package com.example.indroydlab.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation.NavigatorProvider
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer
import com.example.indroydlab.ui.navigation.Routes
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

//class NavigationState(
//    val startRoutes: NavKey,
//    topLevelRoutesState: MutableState<NavKey>,
//    val backStack: Map<NavKey, NavBackStack<NavKey>>
//) {
//    var topLevelRoutes by topLevelRoutesState
//
//    val stacksInUse: List<NavKey>
//        get() = if (topLevelRoutes == startRoutes) {
//            listOf(startRoutes)
//
//        } else {
//            listOf(startRoutes, topLevelRoutes)
//        }
//}
//
//@Composable
//fun rememberNavigationState(
//    startRoutes: NavKey,
//    topLevelRoutes: Set<NavKey>
//): NavigationState {
//
//    val topLevelRoutesState = rememberSerializable(
//        startRoutes,
//        topLevelRoutes,
//        serializer = MutableStateSerializer(PolymorphicSerializer(NavKey::class))
//    ) {
//        mutableStateOf(startRoutes)
//    }
//
//    val backStackMap = topLevelRoutes.associateWith { key ->
//        rememberNavBackStack(key)
//    }
//
//    return remember(startRoutes, topLevelRoutesState) {
//        NavigationState(
//            startRoutes,
//            topLevelRoutesState,
//            backStackMap
//        )
//    }
//}
//
//@Composable
//fun NavigationState.toEntries(
//    entryProvider: (NavKey) -> NavEntry<NavKey>
//): SnapshotStateList<NavEntry<NavKey>> {
//    val decoratedEntries = backStack.mapValues { (_,stack) ->
//        val decorators = listOf(
//            rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
//            rememberViewModelStoreNavEntryDecorator()
//        )
//        rememberDecoratedNavEntries(
//            backStack = stack,
//            entryProvider = entryProvider,
//            entryDecorators = decorators
//        )
//    }
//    return stacksInUse
//        .flatMap { decoratedEntries[it] ?: emptyList()  }
//        .toMutableStateList()
//}
