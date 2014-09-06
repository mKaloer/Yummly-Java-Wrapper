package com.kaloer.yummly;

import java.io.IOException;
import java.util.ArrayList;

import com.kaloer.yummly.models.Flavors;
import com.kaloer.yummly.models.NutritionRange;
import com.kaloer.yummly.models.NutritionRange.NUTRITION;
import com.kaloer.yummly.models.Recipe;
import com.kaloer.yummly.models.SearchResult;

public class Main {

	public static void main(String[] args) {
		Yummly y = new Yummly("YOUR_APP_ID", "YOUR_APP_KEY");
		SearchResult result;
		try {
			Flavors minFlavors = new Flavors();
			minFlavors.setPiquant(0.8);
			ArrayList<NutritionRange> nutrition = new ArrayList<NutritionRange>();
			NutritionRange range = new NutritionRange();
			range.setMin(5);
			range.setMax(100);
			range.setNutrition(NUTRITION.PROCNT);
			nutrition.add(range);
			result = y.search("Chicken", true, null, null, -1, minFlavors, null, true, true, nutrition);
			/*
			for (Recipe recipe : result.getMatches()) {
				System.out.println(String.format("%s, (%d), (%s) %s", recipe.getName(), recipe.getRating(), recipe.getId(), recipe.getImages().get(0).getHostedSmallUrl()));
			}
			*/
			Recipe recipe = y.getRecipe(result.getMatches().get(0).getId());
			System.out.println(String.format("%s, (%d), (%s) %s", recipe.getName(), recipe.getRating(), recipe.getId(), recipe.getImages().get(0).getHostedSmallUrl()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
