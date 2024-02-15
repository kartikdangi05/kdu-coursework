const recipesContainer : HTMLElement | null = document.getElementById("recipes-container");

function createRecipeElement(recipe: Recipe): HTMLDivElement {
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

const handleFrontend = (recipes : Recipe[]) : void => {
    if(recipesContainer != null){
        recipesContainer.innerHTML = '';
        recipes.forEach(recipe => {
            const recipeElement = createRecipeElement(recipe);
            recipesContainer.appendChild(recipeElement);
        });
    }
}

const searchRecipesFromFrontend = () : void => {
    const query : HTMLInputElement | null = document.getElementById("search") as HTMLInputElement | null;
    if(query != null){
        const queryVal : string = query.value;
        searchRecipes(queryVal);
    }
}


interface Recipe {
    image: string;
    name: string;
    rating: number;
    cuisine: string;
    ingredients: string[];
    difficulty: string;
    instructions: string[];
    timeTaken: number;
    caloriesPerServing: number;
}

interface WholeRecipeObj {
    id: number;
    name: string;
    ingredients: string[];
    instructions: string[];
    prepTimeMinutes: number;
    cookTimeMinutes: number;
    servings: number;
    difficulty: string;
    cuisine: string;
    caloriesPerServing: number;
    tags: string[];
    userId: number;
    image: string;
    rating: number;
    reviewCount: number;
    mealType: string[];
}

const mapToRecipe = (recipesObj : WholeRecipeObj[]) : Recipe[] => {
    const recipes: Recipe[] = recipesObj.map((item: WholeRecipeObj) => ({
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
}

let globalRecipes: Recipe[] = [];

const fetchRecipesFromAPI = (): Promise<Recipe[]> => {
    return new Promise((resolve, reject) => {
        fetch("https://dummyjson.com/recipes")
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Error fetching data from API!");
                }
                return response.json();
            })
            .then((response) => {
                const recipes : Recipe[] = mapToRecipe(response.recipes);
                resolve(recipes);
            })
            .catch((error) => {
                console.log("Error: ", error);
                reject([]);
            });
    });
};

const assignRecipesToGlobalVariable = async (): Promise<void> => {
    try {
        const recipes: Recipe[] = await fetchRecipesFromAPI();
        globalRecipes = recipes;
    } catch (error) {
        console.error("Error fetching recipes:", error);
    }
};

assignRecipesToGlobalVariable();

const printAllRecipes = (recipes : Recipe[]): void => {
    recipes.forEach((item: Recipe, index: number) => {
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

const searchRecipes = (query : string) : Promise<Recipe[]> => {
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