package com.example.funfactassignment

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.funfactassignment.FunFact
import com.example.funfactsassignment.ui.theme.FunFactsTheme




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**** TODO: UPDATE ME AS NEEDED *****/
        /* Something like
        val vm: FactsViewModel by viewModels(){
            FactsViewModel.Factory((application as FunFactsApplication).repository)}

        */

        setContent {
            FunFactsTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(floatingActionButton = {
                    ExtendedFloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        onClick = {
                            //TODO: UPDATE ME!!!
                            //something like vm.fetchFact()

                        }){
                        Text(color = MaterialTheme.colorScheme.onTertiaryContainer,
                            text ="Fetch Fact")
                    }}) {
                    Surface(
                        modifier = Modifier.padding(it).fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Column {

                            Card(modifier = Modifier.padding(8.dp).fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primaryContainer)

                            ) {
                                Text(
                                    text = "Fun Facts!",
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.headlineLarge
                                )
                            }
                            //TODO UPDATE ME!!!
                            //Something like
                            //This for phase 1: val list = vm.fakeData
                            //This for phase 2:
                            //val list by vm.facts.collectAsState(listOf())
                            val list = listOf(FunFact("REPLACE ME", "https://cs.utah.edu"))
                            LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                for (fact in list!!.asReversed()) {
                                    item {
                                        Fact(fact)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Fact(fact: FunFact, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .background(MaterialTheme.colorScheme.surface)
        .padding(8.dp)
        .fillMaxWidth()
    ) {
        Text(
            text = fact.text,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier.padding(16.dp)
        )
        val uriHandler = LocalUriHandler.current
        Button(
            modifier = modifier.padding(16.dp),
            contentPadding = PaddingValues(top = 0.dp, bottom = 0.dp, start = 16.dp, end = 16.dp),
            onClick = {
                uriHandler.openUri(fact.source_url)
            }){
            Text(
                text = "Source",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(0.dp),
            )
        }
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
fun FactPreview() {
    FunFactsTheme {
        Fact(FunFact("Interesting fact!", "https://some.fact.com/thefact"))
    }
}