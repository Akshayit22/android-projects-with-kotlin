package com.example.myfirstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstapplication.ui.theme.MyFirstApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        /*
        val peoples = listOf(
            Person("John", "Smith", 25),
            Person("Emma", "Johnson", 30),
            Person("Oliver", "Brown", 28),
            Person("Sophia", "Davis", 24),
            Person("Liam", "Wilson", 26),
            Person("Isabella", "Martinez", 27),
            Person("Noah", "Anderson", 29),
            Person("Mia", "Thomas", 22),
            Person("James", "Garcia", 31),
            Person("Charlotte", "Harris", 23),
            Person("Ethan", "Clark", 33),
            Person("Amelia", "Lewis", 21),
            Person("Benjamin", "Robinson", 32),
            Person("Harper", "Walker", 26),
            Person("Lucas", "Hall", 27),
            Person("Evelyn", "Allen", 29),
            Person("Henry", "Young", 34),
            Person("Abigail", "King", 25),
            Person("Alexander", "Wright", 28),
            Person("Ella", "Scott", 24)
        )
        val filteredPeoples = peoples.filter { it.age > 24 && it.firstName.startsWith("A") }
        */

        val itemsList = listOf(
            Items("Tech Reviews", "Watch reviews of the latest gadgets", ItemType.VIDEO),
            Items("Nature Photography", "Explore stunning pictures of nature", ItemType.IMAGE),
            Items("Introduction to Coroutines", "Learn how to use Kotlin Coroutines effectively", ItemType.TEXT),
            Items("Music Video", "Enjoy the latest music videos", ItemType.VIDEO),
            Items("Family Memories", "Cherished moments captured in photos", ItemType.IMAGE),
            Items("Welcome to Kotlin", "Learn the basics of Kotlin programming", ItemType.TEXT),
            Items("Jetpack Compose Guide", "A guide to building UI with Jetpack Compose", ItemType.TEXT),
            Items("Workout Routines", "Step-by-step workout guides", ItemType.VIDEO),
            Items("Wildlife Gallery", "Discover amazing photos of wildlife", ItemType.IMAGE),
            Items("Tips for Clean Code", "Best practices for writing clean and maintainable code", ItemType.TEXT),
            Items("Cooking Tips Video", "Learn quick and easy recipes", ItemType.VIDEO),
            Items("Art Showcase", "A collection of beautiful artworks", ItemType.IMAGE),
            Items("Travel Vlog", "Explore beautiful destinations through this vlog", ItemType.VIDEO),
            Items("Android Development", "Introduction to Android app development", ItemType.TEXT),
            Items("Architecture Designs", "Unique and inspiring architectural photography", ItemType.IMAGE),
            Items("Beautiful Sunset", "A stunning view of the sunset over the mountains", ItemType.IMAGE),
            Items("Nature Photography", "Explore stunning pictures of nature", ItemType.IMAGE),
            Items("Relaxing Music Video", "Soothing instrumental visuals", ItemType.VIDEO),
            Items("Mindfulness Guide", "Improve mindfulness with simple steps", ItemType.TEXT),
            Items("Latest Movie Review", "Insights on the newest blockbusters", ItemType.TEXT)
        )

        setContent {
            MyFirstApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        items(itemsList) {
                            if(it.type == ItemType.VIDEO){
                                ItemCardVideo(it)
                            }else if (it.type == ItemType.TEXT){
                                ItemCardText(it)
                            }else{
                                ItemCardImage(it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCardText(item: Items) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            text = item.title,
            modifier = Modifier.padding(5.dp)
        )

        Text(
            text = item.text,
            modifier = Modifier.padding(5.dp)
        )

    }
}

@Composable
fun ItemCardVideo(item: Items) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            text = "Click Below to play video",
            modifier = Modifier.padding(5.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.person_template),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
        )
    }
}

@Composable
fun ItemCardImage(item: Items) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            text = "Image",
            modifier = Modifier.padding(5.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.person_template),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello, $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyFirstApplicationTheme {
        Greeting("Android")
    }
}


@Composable
fun CardView(person: Person) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.person_template),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
            )

            Column {
                Text(
                    text = person.firstName,
                    modifier = Modifier.padding(top = 5.dp)
                )
                Text(
                    text = person.lastName,
                    modifier = Modifier.padding(0.dp)
                )
                Text(
                    text = "Age: " + person.age,
                    modifier = Modifier.padding(0.dp)
                )
            }

        }
    }
}