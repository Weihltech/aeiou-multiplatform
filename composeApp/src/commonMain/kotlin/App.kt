import androidx.compose.runtime.Composable
import pages.home.HomeContent

@Composable
fun App() {
    HomeContent()
}

//@OptIn(ExperimentalResourceApi::class)
//@Composable
//fun App2() {
//    AppTheme {
//        var greetingText by remember { mutableStateOf("Hello World!") }
//        var showImage by remember { mutableStateOf(false) }
//
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = {
//                greetingText = "Compose: ${Greeting().greet()}"
//                showImage = !showImage
//            }) {
//                Text(greetingText)
//            }
//            AnimatedVisibility(showImage) {
//                Image(
//                    painterResource("compose-multiplatform.xml"),
//                    null
//                )
//            }
//            HomeBottomBar {
//
//            }
//        }
//    }
//}