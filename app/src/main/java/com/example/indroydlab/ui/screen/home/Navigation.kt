package com.example.indroydlab.ui.screen.home

//import androidx.navigation3.runtime.NavKey
//
//class Navigator(val state: NavigationState) {
//
//    fun navigate(route: NavKey) {
//        if(route in state.backStack.keys) {
//            state.topLevelRoutes = route
//        } else {
//            state.backStack[state.topLevelRoutes]?.add(route)
//        }
//    }
//
//    fun goBack() {
//        val currentStack = state.backStack[state.topLevelRoutes]
//            ?: error("Back stack for ${state.topLevelRoutes} doesn't exist")
//        val currentRoute = currentStack.last()
//
//        if(currentRoute == state.topLevelRoutes) {
//            state.topLevelRoutes = state.startRoutes
//        } else {
//            currentStack.removeLastOrNull()
//        }
//    }
//}