# This is the assets folder
You can put you mod assets here.

# Structure
Sprites should be placed in `assets/sprites`, otherwise they won't be added to the texture atlas
and therefore will not be findable via `Core.atlas.find`.

Sprites that override vanilla sprites belong to `assets/sprites-override`.

Internationalization (i18n) bundle files should be placed in `assets/bundles`
and have the name `bundle_KIND.properties`, where `KIND` is the kind of the bundle.
The primary bundle file should be named `bundle.properties`.

Custom assets can be placed in any subfolder (you will have to load them manually).

# Note
This file (`README.md`) is treated as an asset too. You should delete it before releasing the mod.
