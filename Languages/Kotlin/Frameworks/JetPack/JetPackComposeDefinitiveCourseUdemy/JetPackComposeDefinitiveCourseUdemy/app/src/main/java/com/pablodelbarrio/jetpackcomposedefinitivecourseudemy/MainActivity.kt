package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.core.network.NetworkModule
import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.data.LoginRepository
import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.data.network.LoginService
import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.domain.LoginUseCase
import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.ui.LoginScreen
import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.ui.LoginViewModel
import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.ui.theme.JetPackComposeDefinitiveCourseUdemyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeDefinitiveCourseUdemyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(loginViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        // With the modifier we can change any component properties, like height, color, clickable behaviour...
        modifier = modifier
            .background(Color.Gray)
            .fillMaxSize()
            .padding()
            .clickable {

            }
    )
}

/*
    Limitations the preview function can't receive parameters
    We can have multiple previews to
    The preview can receive many parameters like show background, showSystemUi, device to test...
*/
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetPackComposeDefinitiveCourseUdemyTheme {
        LoginScreen(
            LoginViewModel(
                LoginUseCase(
                    LoginRepository(
                        LoginService(
                            NetworkModule().provideLoginClient(
                                NetworkModule().provideRetrofit()
                            )
                        )
                    )
                )
            )
        )
    }
}

/*
    Dagger Hilt = Dependency Injection
    Ways tho inject
        - The application -> must extend from Application() and also must have this annotation @HiltAndroidApp
        - The activities -> must have this annotation @AndroidEntryPoint, when an activity needs a View and this view
          needs her model we can get it like this private val loginViewModel: LoginViewModel by viewModels()
        - The model must be annotated with @HiltViewModel (also extend ViewModel no dagger hilt requirement)
        - Any class witch needs a property can be declared like that @Inject constructor(private val loginUseCase: LoginUseCase)
          and dagger hilt will inject it
        - Last injection way it's the provider one, this is like create a bean in Spring, see NetworkModule
*/