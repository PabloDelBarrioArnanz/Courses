//
//  LinkWebView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 19/12/23.
//

import SwiftUI

struct LinkWebView: View {
    
    private let url = "https://www.moure.dev"
    
    @State private var showWebKitView = false
    @State private var showSafariWebView = false
    
    var body: some View {
        VStack(spacing: 20) {
            Link(destination: URL(string: url)!) {
                HStack {
                    Image(systemName: "link")
                    Text("Visita moure.dev")
                }
            }
            
            Button("Abrir WebKitView") {
                showWebKitView = true
            }.sheet(isPresented: $showWebKitView) {
                WebKitView(url: url)
            }
            
            Button("Abrir SafariWebView") {
                showSafariWebView = true
            }.sheet(isPresented: $showSafariWebView) {
                SafariWebView(url: url)
            }
        }
    }
}

#Preview {
    LinkWebView()
}
