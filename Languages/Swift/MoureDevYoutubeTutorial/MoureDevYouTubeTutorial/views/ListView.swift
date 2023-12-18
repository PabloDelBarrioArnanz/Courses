//
//  ListView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 18/12/23.
//

import SwiftUI

final class ProgrammerModelData: ObservableObject {
    // With this annotation, modifications made in this variable will be publicated
    @Published var programmers = [
        Programmer(id: 0, name: "Pablo", languages: "Java, Kotlin, Swift", avatar: Image(systemName: "person.fill"), isFavourite: false),
        Programmer(id: 1, name: "Laura", languages: "Java, Kotlin, Swift", avatar: Image(systemName: "person.fill"), isFavourite: true),
        Programmer(id: 2, name: "Ines", languages: "Java, Kotlin, Swift", avatar: Image(systemName: "person.fill"), isFavourite: true),
        Programmer(id: 3, name: "Jesus", languages: "Java, Kotlin, Swift", avatar: Image(systemName: "person.fill"), isFavourite: false),
        Programmer(id: 4, name: "Susana", languages: "Java, Kotlin, Swift", avatar: Image(systemName: "person.fill"), isFavourite: false)
    ]
}

struct ListView: View {
    
    // Annotation to allow al subviews access to his values
    @EnvironmentObject var programmersModelData: ProgrammerModelData
    
    // States are props witch when change make our interface change
    @State private var showingOnlyFav = false
    
    // Calculated property, it's computed when required, defined with {}
    private var filteredProgrammers: [Programmer] {
        programmersModelData.programmers.filter { programmer in
            !showingOnlyFav || programmer.isFavourite
        }
    }
    
    var body: some View {
        NavigationView { // Allows us to interconnect views, go back buttom
            VStack {
                // Toggle, needs a state to change, not a constant like true/false and dolar to make it mutable
                Toggle(isOn: $showingOnlyFav) {
                    Text("Mostrar favoritos")
                }
                .padding()
            
                List(filteredProgrammers, id: \.id) { programmer in
                    // Makes item a navigation button
                    NavigationLink(destination: ListDetailView(programmer: programmer, favourite: $programmersModelData.programmers[programmer.id].isFavourite)) {
                        RowView(programmer: programmer)
                    }
                }
            }.navigationTitle("Programadores")
        }
    }
}

#Preview {
    ListView().environmentObject(ProgrammerModelData())
}
