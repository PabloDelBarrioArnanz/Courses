//
//  EnvironmentView.swift
//  MoureDevYouTubeTutorial
//
//  Created by BW33GB on 18/12/23.
//

import SwiftUI

struct EnvironmentView: View {
    
    @EnvironmentObject var user: UserData // Similar to binding object but automatic from caller, it's for an object shared between many views
    
    var body: some View {
        Text(user.name)
    }
}

#Preview {
    EnvironmentView().environmentObject(UserData())
}
