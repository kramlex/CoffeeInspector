//
//  ProfileViewModel.swift
//  iosApp
//
//  Created by Mark Dubkov on 13.02.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import Combine
import MultiPlatformLibrary
import KMPNativeCoroutinesCombine
import KSwift

class ProfileViewModel {
    
    let profileStatePublisher: AnyPublisher<ProfileModelKs, Never>
    
    init() {
        let useCase = AppComponent.shared.profileUseKeys
        let publisher: AnyPublisher<ProfileModel, Error> = createPublisher(for: useCase.observeProfileModelNative)
        
        profileStatePublisher = publisher.map {
            ProfileModelKs($0)
        }.assertNoFailure().receive(on: RunLoop.main).eraseToAnyPublisher()
    }
}
