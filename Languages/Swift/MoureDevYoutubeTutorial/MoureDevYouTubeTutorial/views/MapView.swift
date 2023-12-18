//
//  MapView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 18/12/23.
//

import SwiftUI
import MapKit

struct MapView: View {
    var body: some View {
        Map {
            Marker("Casa", coordinate: CLLocationCoordinate2D(latitude: 40.375645, longitude: -3.619506))
                .tint(.blue)
            Annotation("Diller Civic Center Playground", coordinate: CLLocationCoordinate2D(latitude: 40.381306, longitude: -3.622620)) {
                ZStack {
                    RoundedRectangle(cornerRadius: 5)
                        .fill(Color.yellow)
                    Text("😡")
                        .padding(5)
                }
            }
        }
    }
}

#Preview {
    MapView()
}
