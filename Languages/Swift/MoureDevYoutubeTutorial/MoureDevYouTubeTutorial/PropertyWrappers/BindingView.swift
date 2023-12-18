//
//  BindingView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 18/12/23.
//

import SwiftUI

struct BindingView: View {
    
    
    @Binding var value: Int // It's like a state but for a property witch this view it's not the owner
    @ObservedObject var user: UserData // Same as Binding but for objects
    @State private var selection: Int?
    
    var body: some View {
        VStack {
            Text("El valor actual es \(value)")
            Button("Suma 2") {
                value += 2
            }
            Text("Mi nombre \(user.name) y mi edad \(user.age)")
            Button("Actualizar datos") {
                user.name = "Pablo From Binding"
                user.age = 27
            }
            NavigationLink(destination: EnvironmentView(), tag: 1, selection: $selection) {
                Button("Ir a EnvironmentView") {
                    selection = 1
                }
            }
        }.padding()
    }
}

#Preview {
    BindingView(value: .constant(0), user: UserData())
}
