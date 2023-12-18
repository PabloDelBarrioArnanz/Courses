//
//  ListDetailView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 18/12/23.
//

import SwiftUI

struct ListDetailView: View {
    
    var programmer: Programmer
    
    // Like a State but initializable by caller and modified value will be setted into calling value
    @Binding var favourite: Bool
    
    var body: some View {
        VStack {
            programmer.avatar
                .resizable()
                .frame(width: 200, height: 200)
                .clipShape(/*@START_MENU_TOKEN@*/Circle()/*@END_MENU_TOKEN@*/)
                .overlay(Circle()
                    .stroke(.black, lineWidth: 4))
                .shadow(radius: 5)
            HStack {
                Text(programmer.name).font(.largeTitle)
                Button {
                    favourite.toggle()
                } label: {
                    if (favourite) {
                        Image(systemName: "star.fill").foregroundColor(.yellow)
                    } else {
                        Image(systemName: "star").foregroundColor(.black)
                    }
                }
            }
            Text(programmer.languages).font(.title)
        }
        Spacer()
    }
}

#Preview {
    ListDetailView(programmer: Programmer(id: 1, name: "Pablo", languages: "Java, Kotlin, Swift", avatar: Image(systemName: "person.fill"), isFavourite: true), favourite: .constant(true))
}
