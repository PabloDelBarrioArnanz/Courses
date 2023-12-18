//
//  ImageView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 18/12/23.
//

import SwiftUI

/*
    Images are save in assets folder
    There we can set the tree sizes icon
    And we can select properties like resizing, compression..
    Also we can select a image to use only for some devices and for dark/white theme
*/

struct ImageView: View {
    var body: some View {
        // Stack it's for return more than one view
        VStack {
            // This chain order it's rlly important, due props are applied sequentially to prev view
            Image("recipeIcon") // By default uses max size
                .resizable() // to adjust image size to screen size
                .padding(20)
                //.scaledToFit() // to scale max horizontal o vertical
                .frame(width: 300, height: 300) // static
                .background(.gray)
                .clipShape(Rectangle())
                .overlay(Circle().stroke(.blue, lineWidth: 4))
                .shadow(radius: 5)
            
            Image(systemName: "person.fill.badge.minus")
                .resizable()
                .padding(20)
                .frame(width: 300, height: 300)
                .background(.gray)
                .clipShape(Circle())
                .overlay(Circle().stroke(.blue, lineWidth: 4))
                .shadow(radius: 5)
                .foregroundColor(/*@START_MENU_TOKEN@*/.blue/*@END_MENU_TOKEN@*/)
        }
            
    }
}

#Preview {
    ImageView()
}
