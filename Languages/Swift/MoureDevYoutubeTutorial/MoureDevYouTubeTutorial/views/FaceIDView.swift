//
//  FaceIDView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 19/12/23.
//

import SwiftUI
import LocalAuthentication

struct FaceIDView: View {
    
    @State private var auth = false
    
    var body: some View {
        VStack {
            Text(auth ? "Estas autenticado" : "Necesitas autenticarte").font(.title).bold()
            Button(auth ? "Cerrar" : "Autenticar") {
                if auth {
                    auth = false
                } else {
                    autenticate()
                }
            }
            .buttonStyle(.borderedProminent)
            .buttonBorderShape(.roundedRectangle)
            .accentColor(.black)
        }
        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: .infinity)
        .background(auth ? .green : .red)
    }
    
    private func autenticate() {
        var error: NSError? // this is a objective C flow var due authentication framework not's migrated yet
        let laContext = LAContext()
        
        if laContext.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &error) { // memory reference ObjectiveC
            laContext.evaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, localizedReason: "Autenticate usando el sensor biométrico") { authenticated, error in
                if authenticated {
                    auth = true
                }
            }
        } else {
            auth = true // no sense but only for the test
        }
    }
}

#Preview {
    FaceIDView()
}
