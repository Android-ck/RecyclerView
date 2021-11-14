Steps

- add recyclerview dependencies in gradle
- create item xml layout
- add <RecyclerView> to activity or fragment layout
- create model that contain item data
- create an adapter for recyclerview
- setup recyclerview settings [ fixedSize, layoutManager, adapter ]

Item Click

- create an interface with all methods want to triggered when use clicked on item
- make adapter contractor receive instance of that interface
- set onClickListener to item view in ViewHolder
- update the recyclerview adapter in activity to receive item after clicked