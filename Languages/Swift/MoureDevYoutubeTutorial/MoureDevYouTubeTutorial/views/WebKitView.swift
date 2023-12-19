//
//  WKWebView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 19/12/23.
//

import SwiftUI
import WebKit

struct WebKitView: UIViewRepresentable {
    
    let url: String
    
    func makeUIView(context: Context) -> WKWebView {
        return WKWebView()
    }
    
    func updateUIView(_ uiView: WKWebView, context: Context) {
        uiView.load(URLRequest(url: URL(string: url)!))
    }
    
}

#Preview {
    WebKitView(url: "https://moure.dev")
}
