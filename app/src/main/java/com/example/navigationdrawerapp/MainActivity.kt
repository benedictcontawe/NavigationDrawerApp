package com.example.navigationdrawerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationdrawerapp.ui.theme.NavigationDrawerAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()//.setKeepOnScreenCondition { true }
        setContent {
            val viewModel : DashboardViewModel = viewModel { DashboardViewModel() }
            val list : List<DrawerModel> by viewModel.getLiveList().observeAsState( listOf<DrawerModel>() )
            val coroutineScope = rememberCoroutineScope()
            val navController : NavHostController = rememberNavController()
            val drawerState : DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            NavigationDrawerAppTheme {
                ModalNavigationDrawer (
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            DrawerHeaderComposable()
                            DrawerBodyComposable (
                                models = list,
                                onHeaderCellClick = {  index, model ->
                                    viewModel.onHeaderCellClick(model, index)
                                }, onContentCellClick = { model -> coroutineScope.launch {
                                    navController.navigate("${Constants.KEY_CONTENT}/${model.text}")
                                    drawerState.close()
                                } }
                            )
                        }
                    }
                ) {
                    Scaffold (
                        topBar = {
                            AppBarComposable { coroutineScope.launch {
                                drawerState.open()
                            } } },
                        content = { paddingValues ->
                            Surface (
                                modifier = Modifier.fillMaxSize().padding(paddingValues),
                                color = MaterialTheme.colorScheme.background
                            ) {
                                NavHostComposable(navController = navController)
                            }
                        }
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun AppBarComposable (onNavigationIconClick : () -> Unit,) {
        TopAppBar (
            title = {
                Text( text = stringResource(id = R.string.app_name) )
            },
            navigationIcon = {
                IconButton(onClick = onNavigationIconClick) {
                    Icon (
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                    )
                }
            }
        )
    }

    @Composable
    private fun DrawerHeaderComposable() {
        Box (modifier = Modifier.fillMaxWidth().padding(vertical = 64.dp,)) {
            Text(text = "Header", fontSize = 60.sp)
        }
    }

    @Composable
    private fun DrawerBodyComposable (
        models : List<DrawerModel>,
        modifier : Modifier = Modifier,
        modelTextStyle : TextStyle = TextStyle(fontSize = 18.sp),
        onHeaderCellClick : (DrawerModel, Int) -> Unit, onContentCellClick : (DrawerModel) -> Unit,
    ) {
        LazyColumn(modifier = modifier, content = {
            itemsIndexed(models) { index : Int, model : DrawerModel ->
                if (model.isHeader) HeaderCellComposable (
                    model,
                    modelTextStyle,
                    { onHeaderCellClick(model, index,) }
                )
                else if (model.isHeader.not() && model.isExpand) ContentCellComposable (
                    model,
                    modelTextStyle,
                    { onContentCellClick(model) }
                )
            }
        } )
    }

    @Composable
    private fun HeaderCellComposable (model : DrawerModel, modelTextStyle : TextStyle, onCellClick : (DrawerModel) -> Unit) {
        Row (
            modifier = Modifier.fillMaxWidth()
                .clickable {
                    onCellClick(model)
                }
                .padding(16.dp)
        ) {
            Icon (
                contentDescription = null,
                painter  = painterResource(id = model.icon)
            )
            Spacer( modifier = Modifier.width(16.dp) )
            Text (
                text = model.text,
                style = modelTextStyle,
                modifier = Modifier.weight(1f)
            )
            Icon (
                imageVector = if(model.isExpand) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }
    }

    @Composable
    private fun ContentCellComposable (model : DrawerModel, modelTextStyle : TextStyle, onCellClick : (DrawerModel) -> Unit) {
        Row (
            modifier = Modifier.fillMaxWidth()
                .clickable {
                    onCellClick(model)
                }
                .padding(16.dp)
        ) {
            Icon (
                contentDescription = null,
                painter  = painterResource(id = model.icon)
            )
            Spacer( modifier = Modifier.width(16.dp) )
            Text (
                text = model.text,
                style = modelTextStyle,
                modifier = Modifier.weight(1f)
            )
        }
    }

    @Composable
    private fun NavHostComposable(navController : NavHostController) {
        NavHost(navController = navController, startDestination  = "main") {
            composable(route = Constants.ROUTE_MAIN) { backStackEntry : NavBackStackEntry ->
                Box (
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text (
                        text = stringResource( id = R.string.app_name ),
                        textAlign = TextAlign.Center,
                    )
                }
                /*
                LaunchedEffect(Unit) {
                    navController.navigate(route = Constants.ROUTE_MAIN) {
                        popUpTo(route = "splash") {
                            inclusive = true
                        }
                    }
                }
                */
            }
            composable(route = Constants.ROUTE_CONTENT) { backStackEntry : NavBackStackEntry ->
                val content = backStackEntry.arguments?.getString(Constants.KEY_CONTENT)
                Box (
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text (
                        text = "${stringResource( id = R.string.content )} $content",
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}