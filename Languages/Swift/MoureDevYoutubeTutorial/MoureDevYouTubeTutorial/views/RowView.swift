//
//  RowView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 18/12/23.
//

import SwiftUI

// Any of the rows for the table

struct RowView: View {
    
    var programmer: Programmer
    
    var body: some View {
        HStack {
            programmer.avatar
                .resizable()
                .frame(width: 40, height: 40)
                .padding()
            VStack(alignment: .leading, content: {
                Text(programmer.name)
                    .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
                Text(programmer.languages)
                    .font(.subheadline)
            })
            Spacer()
            
            if (programmer.isFavourite) {
                Image(systemName: "star.fill")
                    .foregroundColor(.yellow)
            }
        }
    }
}

#Preview {
    // As we define a property in this view, the preview needs it only for test
    RowView(programmer: Programmer(id: 1, name: "Pablo", languages: "Java, Kotlin, Swift", avatar: Image(systemName: "person.fill"), isFavourite: true))
}
