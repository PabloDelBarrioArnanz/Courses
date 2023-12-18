//
//  ContentView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 9/12/23.
//

import SwiftUI

struct StackView: View {
    
    /*
     Content view must return a View contract (only one)
     To select a view we can use the plus icon upper right and search the view style we want, for example stack
     Also we can use the view directly from code
    */
    var body: some View {
        // This is a verticalk stack view, the stack max size it's 10
        VStack(spacing: 15) {
            Text("Hello from line 1")
                .padding()
                .foregroundColor(Color.white)
                .background(Color.black)
            Text("Hello from line 2")
                .padding()
                .foregroundColor(Color.white)
                .foregroundColor(Color.white)
                .background(Color.black)
        }
        .padding() // generally if we dont use padding, elements will try to use all screen space
        .background(Color.green)
        
        /*
         This is an horizontal stack
         It's not necessary but we can define two properties in stack initalization
         aligment, by default it's center and spacing
        */
         HStack(alignment: .top,
            spacing: 20) {
            Text("Hello from same line 1")
                .padding()
                .foregroundColor(Color.white)
                .background(Color.black)
            
            Spacer() // push elements as far as possible
            /*
              If we have in a VStack 2 elements and a spacer,
              the first element will be as top as possible and the second one as bottom as possible
            */
             
            Text("Hello from same line 2")
                .padding()
                .foregroundColor(Color.white)
                .background(Color.black)
         }
         .padding()
         .background(Color.red)
        
        ZStack {
            Text("Hello from level 3")
                .padding()
                .foregroundColor(Color.white)
                .background(Color.black)
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/,
                       maxHeight: .infinity)
            Text("Hello from level 2")
                .padding()
                .foregroundColor(Color.white)
                .background(Color.gray)
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/,
                       maxHeight: .infinity)
            Text("Hello from level 1")
                .padding()
                .foregroundColor(Color.white)
                .background(Color.gray)
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/,
                       maxHeight: .infinity)
        }
        .background(Color.yellow)
    }
}

#Preview {
    StackView()
}
