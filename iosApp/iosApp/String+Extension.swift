//
//  String+Extension.swift
//  iosApp
//
//  Created by Mark Dubkov on 13.02.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

extension String {
    var nsString: NSString {
        return NSString(string: self)
    }
}
