const getAllKeysAndValues = (obj) => {
    let keys = [];
    let values = [];
    
    const traverse = (obj) => {
        for(const key in obj) {
            if(typeof obj[key] === "object" && obj[key] !== null) {
                traverse(obj[key]);
            } 
            else{
                keys.push(key);
                values.push(obj[key]);
            }
        }
    }
    traverse(obj);
    return { keys, values };
}

const player = {
    firstName: "Leo",
    lastName: "Messi",
    address: {
        country: "Spain",
        city: "Barcelona",
    },
    careerInfo: {
        fcBarcelona: {
            appearances: 780,
            goals: {
                premierLeagueGoals: 590,
                championsLeagueGoals: 50,
            },
        },
    },
};

const { keys, values } = getAllKeysAndValues(player);


console.log("All the keys:", keys);
console.log("All the values:", values);
