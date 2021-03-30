package com.vnykyt.placesearch.presentation.feature.map

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vnykyt.placesearch.api.model.place.Center
import com.vnykyt.placesearch.api.model.place.Venue
import com.vnykyt.placesearch.presentation.R
import com.vnykyt.placesearch.presentation.databinding.FragmentMapBinding
import com.vnykyt.placesearch.presentation.ui.bitmapDescriptorFromVector
import org.koin.android.viewmodel.ext.android.viewModel

class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {

    private val viewModel by viewModel<MapViewModel>()
    private val viewBinding: FragmentMapBinding by viewBinding()
    private val args by navArgs<MapFragmentArgs>()
    private lateinit var map: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)
        viewModel.search(args.query)

        viewModel.venuesAndGeocode.observe(viewLifecycleOwner, { result ->
            result.venue.forEach { addMarker(it) }
            moveCameraToCityCenter(result.geocode.feature.geometry.center)
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnInfoWindowClickListener { marker ->
            val clickedVenue = requireNotNull(viewModel.venuesAndGeocode.value).venue.find { it.id == (marker.tag as? String) }
            clickedVenue?.let {
                viewBinding.root.findNavController().navigate(MapFragmentDirections.actionPlaceDetailsScreen(it.id, it.distance))
            }
        }
    }

    private fun addMarker(venue: Venue) {
        map.addMarker(
            MarkerOptions()
                .position(LatLng(venue.location.lat, venue.location.lng))
                .snippet(venue.location.formattedAddress.firstOrNull() ?: "")
                .title(venue.name)
                .icon(ContextCompat.getDrawable(requireContext(), R.drawable.ic_place_32).bitmapDescriptorFromVector())
        ).apply {
            tag = venue.id
        }
    }

    private fun moveCameraToCityCenter(center: Center) {
        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(center.lat, center.lng))
            .zoom(13f)
            .build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}