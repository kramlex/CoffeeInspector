//
//  AppComponent.swift
//  iosApp
//
//  Created by Mark Dubkov on 13.02.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import MultiPlatformLibrary

struct AppComponent {
    static var shared: SharedFactory = SharedFactory(settings: AppleSettings(delegate: UserDefaults.standard))
}
