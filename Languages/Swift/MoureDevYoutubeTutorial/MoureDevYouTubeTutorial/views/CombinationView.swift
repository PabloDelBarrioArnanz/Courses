//
//  CombinationView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 18/12/23.
//

import SwiftUI

struct CombinationView: View {
    var body: some View {
        ScrollView {
            VStack {
                Text("Mapa de Vallecas")
                MapView()
                    .frame(height: 400)
                Divider().padding()
                ImageView()
                Divider().padding()
                StackView()
                    .frame(height: 150)
            }
        }
    }
}

#Preview {
    CombinationView()
}
