package com.example.navigationdrawerapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navigationdrawerapp.ui.theme.NavigationDrawerAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel : DashboardViewModel = viewModel { DashboardViewModel() }
            val list : List<DrawerModel> = viewModel.getList()
            val coroutineScope = rememberCoroutineScope()
            //val navController : NavHostController = rememberNavController()
            val drawerState : DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            NavigationDrawerAppTheme {
                ModalNavigationDrawer (
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            DrawerHeaderComposable()
                            DrawerBodyComposable (
                                models = list,
                                onCellClick = { model ->
                                    coroutineScope.launch {
                                        Toast.makeText(getBaseContext(), model.text, Toast.LENGTH_SHORT).show()
                                        drawerState.close()
                                    }
                                }
                            )
                        }
                    }
                ) {
                    Scaffold (
                        topBar = { AppBarComposable {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        } }
                    ) { paddingValues ->
                        Surface (
                            modifier = Modifier.fillMaxSize().padding(paddingValues),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            Text (
                                text = stringResource( id = R.string.app_name ),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
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
        Box (modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp,)) {
            Text(text = "Header", fontSize = 60.sp)
        }
    }

    @Composable
    private fun DrawerBodyComposable (
        models : List<DrawerModel>,
        modifier : Modifier = Modifier,
        modelTextStyle : TextStyle = TextStyle(fontSize = 18.sp),
        onCellClick : (DrawerModel) -> Unit) {
        LazyColumn(modifier = modifier, content = {
            items(models) { model ->
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
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
        } )
    }
}