## Challenge UALA
[Mobile Challenge - v0.5 (1).pdf](https://github.com/user-attachments/files/18397228/Mobile.Challenge.-.v0.5.1.pdf)

# Set up
The project requires a Google maps API key, download the project and set it up on your local.properties as
```
mapsKey = "--your-key--"
```
(If necessary to test it, you can ask me and I can provide my key)

# Libraries
- Retrofit
- Hilt
- Room
- Paging3
- Compose
- JUnit4

# Process
I started setting up the bases of the project, so dependencies and modules, then I started from the back so I created my Models, Entities and DTOs, set up my Repository, API connection, Room Database, and my UseCases.

At this point I was already considering how to tackle the performance, considering the list are very big I wanted to do all operations on the DB to improve performance, the issue I found is that favorites is a local only param so I needed to think how to handle it.

I was considering adding a param to the Entity and DomainModel, and each time the data is updated, check against the ones that are favorites. But this means comparing against the whole list. This is on startup since I implemented SplashAPI so the app starts after everything is ready, but I still wanted to do it in a more efficient way.

Considering my options I decided to create a seperate table of Favorite Ids, this way I'll do a join of the tables and will be able to have a value directly given by the DB to show if it is a favorite, which will help me on the initial load and to show them on the UI.

For the architecture I was thinking of using MVI, but since it has significant more overhead with the MVIStore, Reducer, Middleware, and it was only one screen. I decided to keep it more simple and do MVVM event driven

After adding it I decided to use Paging3 since there where so many entries the LazyColumn would lag.

At this point I already finished mostly the UI and I'm working on the ViewModel and the conection in which I found a couple issues for the way I decided to handle the DB.

Since I was making multiple Queries I couldn't really observe the DB with flow the way I wanted, and since the favorites/name filter must affect both, I had to take some considerations to update both at the same time. To this end I ended up removing those variables from the UIState and implemented them in their own StateFlows, since I needed to observe the changes not only on the UI but also on the VM to update the data.

# Considerations
There are a lot of things that I haven't done cause I don't have time, and I didn't think you would be paying attention to it

- I didn't bother setting up a navgraph (Since it's only one screen)
- Strings and Constants are harcoded there. I could do a Constants file or Theme padding file to add all there, and put all the Strings on the Resources (It's a simple thing that only takes time, so idk if you guys would judge this)
- I just added tests to the DAO (since the sorting/filter logic takes place there)
- Didn't handle an error screen (I just added a Toast, since I thought it's more about how to handle it not the UI result itself)
- Right now the app downloads the data on each start (Ideally I would set up a version of file and only update it if that version changes, so I know that the data from the backend was updated, but that would require setting up a full Firebase remote config and I don't have the time)

And probably many more things, if you guys want any of this things I point, or would like to see anything I missed, you can let me know and I can quickly add it if possible.

Also, on the challenge it said "Provide UI/unit tests for the information screen code we provided you. You are allowed to refactor if needed" So idk if I was supposed to get a UI screen to start with. If you aren't going to but still would like to see some UI test let me know and I can add some

# App running
https://github.com/user-attachments/assets/c06b1933-6efd-4561-90fa-7bc59005178c

# Closing thoughts
There's way too many thing to set up on a new project if you don't have a template (I used to have one but it was too outdated so I decided to update everything and start from 0), dependencies issues, searching for the correct compatible versions of each thing. Overall the start of the project took most of my time.

Regardless the challenge was fun, the thought process of how to handle the data most efficiently was interesting and posed it's problems along the way. I never used Google maps so that was neat as well, way easier than I expected.

Hopefully it's good enough any questions or comment feel free to reach out! Thanks!
