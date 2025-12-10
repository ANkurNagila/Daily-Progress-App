//package com.evc.todolist.screens.home
//
////import androidx.compose.foundation.Image
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.colorResource
//import com.evc.todolist.R
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.tooling.preview.Preview
//
//@Composable
//fun HomeScreen(
//    onProfileClick: () -> Unit = {},
//    onSettingsClick: () -> Unit = {},
//    onDownloadsClick: () -> Unit = {},
//    onTalkToAiClick: () -> Unit = {},
//    onOrganizersClick: () -> Unit = {},
//    onBooksClick: () -> Unit = {},
//    onVideosClick: () -> Unit = {},
//    onNotesClick: () -> Unit = {},
//    onContributeClick: () -> Unit = {},
//    onUploadClick: () -> Unit = {},
//    onSyllabusClick: () -> Unit = {}
//) {
//    Box(
//        modifier = Modifier.fillMaxSize().background(
//            brush = Brush.radialGradient(
//                colors = listOf(
//                    Color(0xFF3A761D),  // highlight_darker
//                    Color(0xFFFFFFFF)   // background_upfront
//                ),
//                center = Offset(x = 0.5f, y = 0.0f), // centerX="50%", centerY="0%"
//                radius = 800f // adjust until it looks similar to your XML's 250dp
//            )
//        )
//    ) {
//
////        Image(
////            painter = painterResource(id = R.drawable.background),
////            contentDescription = "Background Image", // Provide a meaningful description or null
////            contentScale = ContentScale.Crop, // Scales the image to fill the bounds of the Box
////            modifier = Modifier.matchParentSize() // Makes the image match the size of the parent Box
////        )
//
////        Image(
////            painter=painterResource(id=R.drawable.organizer_button),
////            contentDescription="Organiser Button",
////            modifier=Modifier.fillMaxSize()
////        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//                .padding(bottom = 16.dp)
//        ) {
//            // Top Bar
//            TopBar(
//                onProfileClick = onProfileClick,
//                onTalkToAiClick = onTalkToAiClick,
//                onDownloadsClick = onDownloadsClick,
//                onSettingsClick = onSettingsClick
//            )
//
//            // Greeting Section
//            GreetingSection(
//                modifier = Modifier.padding(horizontal = 32.dp, vertical = 32.dp)
//            )
//
//            // Grid Layout
//            ContentGrid(
//                modifier = Modifier.padding(horizontal = 32.dp),
//                onOrganizersClick = onOrganizersClick,
//                onBooksClick = onBooksClick,
//                onVideosClick = onVideosClick,
//                onNotesClick = onNotesClick,
//                onContributeClick = onContributeClick,
//                onUploadClick = onUploadClick,
//                onSyllabusClick = onSyllabusClick
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Ad placeholder
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp)
//                    .padding(horizontal = 32.dp)
//                    .background(Color(0xFF2C2C4A), RoundedCornerShape(12.dp)),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = "Ad Space",
//                    color = Color.Gray,
//                    fontSize = 12.sp
//                )
//            }
//        }
//    }
//}
//
//@Composable
//private fun TopBar(
//    onProfileClick: () -> Unit,
//    onTalkToAiClick: () -> Unit,
//    onDownloadsClick: () -> Unit,
//    onSettingsClick: () -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(32.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        // Profile Picture
//        Box(
//            modifier = Modifier
//                .size(54.dp)
//                .clip(CircleShape)
//                .background(Color(0xFF3A3A5A))
//                .clickable(onClick = onProfileClick),
//            contentAlignment = Alignment.Center
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.avatar),
//                contentDescription = "Profile",
//                tint = Color.White,
//                modifier = Modifier.size(40.dp)
//            )
//        }
//
//        // Ask AI Button
//        Button(
//            onClick = onTalkToAiClick,
//            modifier = Modifier
//                .weight(1f)
//                .padding(horizontal = 8.dp)
//                .height(54.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0x33FFFFFF)
//            ),
//            shape = RoundedCornerShape(16.dp)
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.ai_icon),
//                contentDescription = null,
//                tint = Color.White,
//                modifier = Modifier.size(20.dp)
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(
//                text = "Ask AI",
//                color = Color.White,
//                fontWeight = FontWeight.Bold,
//                fontSize = 14.sp
//            )
//        }
//
//        // Downloads Button
//        IconButton(
//            onClick = onDownloadsClick,
//            modifier = Modifier
//                .size(54.dp)
//                .background(Color(0xFF2C2C4A), RoundedCornerShape(16.dp))
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.downloads),
//                contentDescription = "Downloads",
//                tint = Color.White
//            )
//        }
//
//        Spacer(modifier = Modifier.width(8.dp))
//
//        // Settings Button
//        IconButton(
//            onClick = onSettingsClick,
//            modifier = Modifier
//                .size(54.dp)
//                .background(Color(0xFF2C2C4A), RoundedCornerShape(16.dp))
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_settings),
//                contentDescription = "Settings",
//                tint = Color.White
//            )
//        }
//    }
//}
//
//@Composable
//private fun GreetingSection(modifier: Modifier = Modifier) {
//    Column(modifier = modifier) {
//        Text(
//            text = "Good Morning!",
//            color = Color.White,
//            fontSize = 18.sp
//        )
//
//        Spacer(modifier = Modifier.height(4.dp))
//
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Text(
//                text = "Excel your ",
//                color = Color.White,
//                fontSize = 32.sp,
//                fontWeight = FontWeight.Bold
//            )
//
//            Column {
//                Text(
//                    text = "Exams",
//                    color = Color(0xFFFF6B6B),
//                    fontSize = 32.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                // Underline decoration
//                Box(
//                    modifier = Modifier
//                        .width(80.dp)
//                        .height(4.dp)
//                        .background(
//                            Color(0xFFFF6B6B),
//                            RoundedCornerShape(2.dp)
//                        )
//                )
//            }
//        }
//    }
//}
//
//@Composable
//private fun ContentGrid(
//    modifier: Modifier = Modifier,
//    onOrganizersClick: () -> Unit,
//    onBooksClick: () -> Unit,
//    onVideosClick: () -> Unit,
//    onNotesClick: () -> Unit,
//    onContributeClick: () -> Unit,
//    onUploadClick: () -> Unit,
//    onSyllabusClick: () -> Unit
//) {
//    Column(modifier = modifier) {
//        // First Row: Organizers + Books + Videos
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            // Organizers Card
//            Card(
//                modifier = Modifier
//                    .width(200.dp)
//                    .height(200.dp)
//                    .clickable(onClick = onOrganizersClick),
//                shape = RoundedCornerShape(24.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.Transparent
//                )
//            ) {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.TopEnd
//                ) {
//
//                    Image(
//                        painter=painterResource(id=R.drawable.organizer_button),
//                        contentDescription="Organiser Button",
//                        modifier=Modifier.fillMaxSize()
//                    )
//
//
//                    Text(
//                        text = "Organizers",
//                        color = Color.White,
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Medium,
//                        modifier = Modifier.padding(24.dp)
//                    )
//                }
//            }
//
//            // Books + Videos Column
//            Column(
//                modifier = Modifier.weight(1f),
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                // Books Card
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(92.dp)
//                        .clickable(onClick = onBooksClick),
//                    shape = RoundedCornerShape(24.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = colorResource(R.color.background_lighter)
//                    )
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(16.dp),
//                        contentAlignment = Alignment.TopStart
//                    ) {
//
//                        Image(
//                            painter = painterResource(id=R.drawable.book),
//                            contentDescription ="Organiser Button",
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier.fillMaxSize().padding(top=20.dp,start =20.dp)
//                        )
//
//
//                        Text(
//                            text = "Books",
//                            color = Color.White,
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Medium
//                        )
//                    }
//                }
//
//                // Videos Card
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(92.dp)
//                        .clickable(onClick = onVideosClick),
//                    shape = RoundedCornerShape(24.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color.White
//                    )
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(16.dp),
//                        contentAlignment = Alignment.TopStart
//                    ) {
//
//                        Image(
//                            painter=painterResource(id=R.drawable.youtube),
//                            contentDescription="Video",
//                            modifier=Modifier.fillMaxSize()
//                        )
//
//                        Text(
//                            text = "Videos",
//                            color = Color(0xFF1A1A2E),
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Medium
//                        )
//                    }
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Second Row: Contribute + Upload + Notes
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            // Contribute + Upload Column
//            Column(
//                modifier = Modifier.width(200.dp),
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                // Contribute Card
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(87.dp)
//                        .clickable(onClick = onContributeClick),
//                    shape = RoundedCornerShape(24.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color.White
//                    )
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(16.dp),
//                        contentAlignment = Alignment.TopStart
//                    ) {
//
//                        Image(
//                            painter=painterResource(id=R.drawable.github),
//                            contentDescription="Organiser Button",
//                            contentScale = ContentScale.None,
//                            modifier = Modifier.fillMaxSize()
//                                .padding(start = 80.dp)
//                        )
//
//                        Text(
//                            text = "Contribute",
//                            color = Color(0xFF1A1A2E),
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Medium
//                        )
//                    }
//                }
//
//                // Upload Card
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(87.dp)
//                        .clickable(onClick = onUploadClick),
//                    shape = RoundedCornerShape(24.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = colorResource(R.color.highlight)
//                    )
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(16.dp),
//                        contentAlignment = Alignment.TopStart
//                    ) {
//
//                        Image(
//                            painter = painterResource( id = R.drawable.upload),
//                            contentDescription="Upload",
//                            modifier=Modifier.fillMaxSize().padding()
//                        )
//
//                        Text(
//                            text = "Upload",
//                            color = Color.White,
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Medium
//                        )
//                    }
//                }
//            }
//
//            // Notes Card
//            Card(
//                modifier = Modifier
//                    .weight(1f)
//                    .height(190.dp)
//                    .clickable(onClick = onNotesClick),
//                shape = RoundedCornerShape(24.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = colorResource(R.color.background_upfront)
//                )
//            ) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(24.dp),
//                    contentAlignment = Alignment.TopStart
//                ) {
//
//                    Image(
//                        painter=painterResource(id=R.drawable.notes_button),
//                        contentDescription="Notes Button",
//                        modifier=Modifier.fillMaxSize()
//                    )
//
//                    Text(
//                        text = "Notes",
//                        color = Color.White,
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Medium
//                    )
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Third Row: Syllabus
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(80.dp)
//                .clickable(onClick = onSyllabusClick),
//            shape = RoundedCornerShape(24.dp),
//            colors = CardDefaults.cardColors(
//                containerColor = Color.White
//            )
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(24.dp),
//                contentAlignment = Alignment.CenterStart
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    Text(
//                        text = "Syllabus",
//                        color = Color(0xFF1A1A2E),
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Medium
//                    )
//
//                    // NEW Badge
//                    Surface(
//                        color = Color(0xFFFF6B6B),
//                        shape = RoundedCornerShape(4.dp)
//                    ) {
//                        Text(
//                            text = "NEW",
//                            color = Color.White,
//                            fontSize = 11.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
//                        )
//                    }
//
//                    Image(
//                        painter=painterResource(id=R.drawable.backpack),
//                        contentDescription="Organiser Button",
//                        modifier=Modifier.fillMaxSize()
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun HomeScreenPreview() {
//    MaterialTheme {
//        HomeScreen()
//    }
//}
