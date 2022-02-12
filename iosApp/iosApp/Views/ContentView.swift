import SwiftUI
import MultiPlatformLibrary

struct ContentView: View {

    enum TabCases {
        case profile
    }
    
	var body: some View {
        TabView {
            ProfileView()
                .tag(TabCases.profile)
                .tabItem {
                    Label("Profile", systemImage: "heart.fill")
                }
        }
		
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
