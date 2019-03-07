[![Build Status](https://travis-ci.com/EbenezerGH/ButtonFun.svg?token=z8cBzQiMB7d5QTSrDmYj&branch=master)](https://travis-ci.com/EbenezerGH/ButtonFun)

Tech Stack
==

- 100% Kotlin
- MVVM
- Koin
- Android Architecture Components
- Android X

Reusability Features
==
- AAC's ViewModel that only needs height,width passed.
- Koin Service Locater that allows injectable dependencies anywhere
- Utils module w/ reusuable utilities
- Util level exensions and App level extensions for 

Notes
==
- Because I chose to use a recyclerview to fill the screen with a grid, it provided a different challenge for handling
different orientations.  I decided to calculate how many 40x40 squares fit in the screen, and fit in each column.  With
those values I was able to repopulate the listview with the same data while changing the rows dynamically.

- Viewmodel is greater for persistence however it is no substitute for saved instance state.  The listview data needed
to be saved and retrieved on app launch.  Creating a whole sqlite database with room was overkill,  serializing the list
into a bundle is not recommended.  I decided it was best to just read/write to the disk.  Shared preference is an option
but It is best used for key value pairs.
