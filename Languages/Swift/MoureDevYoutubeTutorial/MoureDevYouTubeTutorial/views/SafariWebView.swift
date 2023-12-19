//
//  SafariWebView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 19/12/23.
//

import SwiftUI
import SafariServices

struct SafariWebView: UIViewControllerRepresentable {
    
    let url: String
    
    func makeUIViewController(context: Context) -> SFSafariViewController {
        return SFSafariViewController(url: URL(string: url)!)
    }
    
    func updateUIViewController(_ uiViewController: SFSafariViewController, context: Context) {
        
    }
    
}

#Preview {
    SafariWebView(url: "https://moure.dev")
}
