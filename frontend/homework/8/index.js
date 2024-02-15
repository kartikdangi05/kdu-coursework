var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const recipesContainer = document.getElementById("recipes-container");
function createRecipeElement(recipe) {
    const recipeElement = document.createElement("div");
    recipeElement.classList.add("recipe");
    const imageElement = document.createElement("img");
    imageElement.src = recipe.image;
    imageElement.alt = recipe.name;
    recipeElement.appendChild(imageElement);
    const nameElement = document.createElement("h2");
    nameElement.textContent = recipe.name;
    recipeElement.appendChild(nameElement);
    const ratingElement = document.createElement("p");
    ratingElement.textContent = `Rating: ${recipe.rating}`;
    recipeElement.appendChild(ratingElement);
    const cuisineElement = document.createElement("p");
    cuisineElement.textContent = `Cuisine: ${recipe.cuisine}`;
    recipeElement.appendChild(cuisineElement);
    const ingredientsElement = document.createElement("ul");
    recipe.ingredients.forEach(ingredient => {
        const ingredientItem = document.createElement("li");
        ingredientItem.textContent = ingredient;
        ingredientsElement.appendChild(ingredientItem);
    });
    recipeElement.appendChild(ingredientsElement);
    const difficultyElement = document.createElement("p");
    difficultyElement.textContent = `Difficulty: ${recipe.difficulty}`;
    recipeElement.appendChild(difficultyElement);
    const instructionsElement = document.createElement("ol");
    recipe.instructions.forEach(instruction => {
        const instructionItem = document.createElement("li");
        instructionItem.textContent = instruction;
        instructionsElement.appendChild(instructionItem);
    });
    recipeElement.appendChild(instructionsElement);
    const timeTakenElement = document.createElement("p");
    timeTakenElement.textContent = `Time Taken: ${recipe.timeTaken} minutes`;
    recipeElement.appendChild(timeTakenElement);
    const caloriesElement = document.createElement("p");
    caloriesElement.textContent = `Calories Per Serving: ${recipe.caloriesPerServing}`;
    recipeElement.appendChild(caloriesElement);
    return recipeElement;
}
const handleFrontend = (recipes) => {
    if (recipesContainer != null) {
        recipesContainer.innerHTML = '';
        recipes.forEach(recipe => {
            const recipeElement = createRecipeElement(recipe);
            recipesContainer.appendChild(recipeElement);
        });
    }
};
const searchRecipesFromFrontend = () => {
    const query = document.getElementById("search");
    if (query != null) {
        const queryVal = query.value;
        searchRecipes(queryVal);
    }
};
const mapToRecipe = (recipesObj) => {
    const recipes = recipesObj.map((item) => ({
        image: item.image,
        name: item.name,
        rating: item.rating,
        cuisine: item.cuisine,
        ingredients: item.ingredients,
        difficulty: item.difficulty,
        instructions: item.instructions,
        timeTaken: item.prepTimeMinutes + item.cookTimeMinutes,
        caloriesPerServing: item.caloriesPerServing
    }));
    return recipes;
};
let globalRecipes = [];
const fetchRecipesFromAPI = () => {
    return new Promise((resolve, reject) => {
        fetch("https://dummyjson.com/recipes")
            .then((response) => {
            if (!response.ok) {
                throw new Error("Error fetching data from API!");
            }
            return response.json();
        })
            .then((response) => {
            const recipes = mapToRecipe(response.recipes);
            resolve(recipes);
        })
            .catch((error) => {
            console.log("Error: ", error);
            reject([]);
        });
    });
};
const assignRecipesToGlobalVariable = () => __awaiter(this, void 0, void 0, function* () {
    try {
        const recipes = yield fetchRecipesFromAPI();
        globalRecipes = recipes;
    }
    catch (error) {
        console.error("Error fetching recipes:", error);
    }
});
assignRecipesToGlobalVariable();
const printAllRecipes = (recipes) => {
    recipes.forEach((item, index) => {
        console.log(`Recipe ${index + 1}:`);
        console.log(`Name: ${item.name}`);
        console.log(`Image: ${item.image}`);
        console.log(`Rating: ${item.rating}`);
        console.log(`Cuisine: ${item.cuisine}`);
        console.log(`Ingredients: ${item.ingredients.join(', ')}`);
        console.log(`Difficulty: ${item.difficulty}`);
        console.log(`Instructions: ${item.instructions.join('\n')}`);
        console.log(`Time Taken: ${item.timeTaken} minutes`);
        console.log(`Calories Per Serving: ${item.caloriesPerServing}`);
        console.log('\n');
    });
};
const searchRecipes = (query) => {
    return new Promise((resolve, reject) => {
        fetch(`https://dummyjson.com/recipes/search?q=${query}`)
            .then((response) => {
            if (!response.ok) {
                throw new Error("Error fetching data from API!");
            }
            return response.json();
        })
            .then((response) => {
            const recipes = mapToRecipe(response.recipes);
            resolve(recipes);
            handleFrontend(recipes);
        })
            .catch((error) => {
            console.log("Error: ", error);
            reject([]);
        });
    });
};
