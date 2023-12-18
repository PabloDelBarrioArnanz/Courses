//
//  StateView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 18/12/23.
//

import SwiftUI

// Observable object to make it mutable from view also modifications make view refresh
class UserData: ObservableObject {
    @Published var name = "Pablo"
    @Published var age = 26
}


/*
    Property Wrappers
        - Way to work with varible state
        - Share states between views
        - Unchanin refreh view process
*/
struct StateView: View {
    
    // His update makes a refresh the text it's update
    @State private var value = 0
    @State private var selection: Int?
    @StateObject private var user = UserData() // StateObject it's like state but for objects
    
    var body: some View {
        NavigationView {
            VStack {
                Text("El valor actual es \(value)")
                Button("Suma 1") {
                    value += 1
                }
                Text("Mi nombre \(user.name) y mi edad \(user.age)")
                Button("Actualizar datos") {
                    user.name = "Pablo From State"
                    user.age = 27
                }
                
                NavigationLink(destination: BindingView(value: $value, user: user), tag: 1, selection: $selection) {
                    Button("Ir a BindingView") {
                        selection = 1
                    }
                }
            }.navigationTitle("StateView")
            .padding()
        }.environmentObject(user)
    }
}

#Preview {
    StateView().environmentObject(UserData())
}
