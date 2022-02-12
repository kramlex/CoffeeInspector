//
//  ProfileUI.swift
//  iosApp
//
//  Created by Mark Dubkov on 13.02.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import KSwift
import MultiPlatformLibrary

struct ProfileView: View {
    
    private let viewModel: ProfileViewModel = ProfileViewModel()
    @State private var model: ProfileModelKs? = nil
    
    
    var body: some View {
        ProfileViewBody(model: model)
            .onReceive(
                viewModel.profileStatePublisher
            ) { model in
                self.model = model
            }
    }
}

struct ProfileViewBody: View {
    let model: ProfileModelKs?
    
    var body: some View {
        switch model {
        case let .unauthorized(unauthorizedModel):
            AuthView(authorize: unauthorizedModel.authorize)
        case let .authorized(authorizedModel):
            ProfileDetails(
                userName: authorizedModel.userModel.userName,
                countCoffeeCup:
                    Int(authorizedModel.userModel.coffeeConsumedAmount),
                logout: authorizedModel.logout
            )
        default:
            ProgressView()
        }
    }
}

struct AuthView: View {
    
    var authorize: ((String) -> Void)? = nil
    
    @State private var name: String = ""
    
    var body: some View {
        NavigationView {
            Form {
                TextField("Enter your name", text: $name)
                Button {
                    authorize?(name)
                } label: {
                    Text("Authorize")
                }
            }
            .navigationTitle("Auth Screen")
        }
    }
}

struct ProfileDetails: View {
    var userName: String
    var countCoffeeCup: Int
    var logout: (() -> Void)? = nil
    
    var body: some View {
        NavigationView {
            VStack {
                Text(userName)
                    .font(.title)
                    .bold()
                Text("I drank \(countCoffeeCup) cups of coffee")
                    .font(.subheadline)
            }
            .navigationTitle("My Profile")
            .toolbar {
                Button {
                    logout?()
                } label: {
                    Label("Logout", systemImage: "arrow.uturn.forward.circle.fill")
                }
            }
        }
    }
}

struct ProfileUI_Previews: PreviewProvider {
    static var previews: some View {
        AuthView(authorize: { _ in })
        ProfileDetails(userName: "Mark", countCoffeeCup: 12)
    }
}
