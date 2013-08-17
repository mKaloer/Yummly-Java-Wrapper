Yummly-Java-Wrapper
===================

A Java wrapper for the Yummly api.
Detailed documentation can be found at the [Yummly developer site](https://developer.yummly.com/documentation/).

## Requirements
This code requires all three [Jackson modules](https://github.com/FasterXML/jackson) for JSON-parsing; "Streaming", "Annotations", and "Databind".

## Usage
All models represents the objects documented at the [Yummly developer site](https://developer.yummly.com/documentation/).
This example shows how to search for spicy salmon recipes. It prints a short description of the returned recipes.

    Yummly y = new Yummly("391aa893", "0c6057e1ecc308f208994a40995f8a3a");
    SearchResult result;
    try {
        Flavors minFlavors = new Flavors();
        // Set miniumum spiciness.
        minFlavors.setPiquant(0.8);
        result = y.search("Salmon");
        for (Recipe recipe : result.getMatches()) {
            System.out.println(String.format("%s, (%d), (%s) %s", recipe.getName(), recipe.getRating(), recipe.getId(), recipe.getImages().get(0).getHostedSmallUrl()));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    
The recipes returned are sparse and do not contain all information. To request a specific recipe containing all information available, use the 'Yummly.GetRecipe()' method with the id of the recipe. This example will request information about the first recipe of the search result set.

    Yummly y = new Yummly("391aa893", "0c6057e1ecc308f208994a40995f8a3a");
    SearchResult result;
    try {
        Flavors minFlavors = new Flavors();
        // Set miniumum spiciness.
        minFlavors.setPiquant(0.8);
        result = y.search("Salmon");
        // Get first recipe.
        Recipe recipe = result.getMatches().get(0);
        // Request detailed information.
        r = y.getRecipe(recipe.getId());
    } catch (IOException e) {
        e.printStackTrace();
    }
