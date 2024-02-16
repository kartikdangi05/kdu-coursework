const socket = io("http://localhost:3001");

socket.on('connect', () => {
    console.log('Connected to server');
});

socket.on('disconnect', () => {
    console.log('Disconnected from server');
});

const historyDiv = document.querySelector(".history");
const buyBtn = document.getElementById("buy-btn");
const sellBtn = document.getElementById("sell-btn");
const grid = document.querySelector(".grid");

const btnHandler = (buyOrSell) => {
    let price = parseFloat(document.getElementById("price").textContent);
    let qty = document.getElementById("qty-input").value;  
    let currentTime = createdDate = new Date().toLocaleString('en-US', { timeZone: 'Asia/Kolkata' });

    const div = document.createElement('div');
    div.classList.add("history-div");

    const div1 = document.createElement('div');
    div1.classList.add('history-price-qty-date');

    const div2 = document.createElement('div');
    if(buyOrSell == "buy"){
        div2.classList.add('history-buy-txn');
        div2.textContent = `Buy`;
    }
    else{
        div2.classList.add('history-sell-txn');
        div2.textContent = `Sell`;
    }

    const p1 = document.createElement('p');
    p1.classList.add("history-price-qty");
    p1.textContent = `${qty} stocks (Price : ${price}) `;

    const p2 = document.createElement('p');
    p2.classList.add("history-date");
    p2.textContent = `${currentTime}`;


    div1.appendChild(p1);
    div1.appendChild(p2);
    div.appendChild(div1);
    div.appendChild(div2);
    historyDiv.appendChild(div);

    socket.emit("save history",div.innerHTML);
}

let currentContainer = 0;
let totalBars = 0;

const handleChange = (newPrice,oldPrice) => {
    const div = document.createElement("div");
    const gridContainer = document.querySelector(`.grid-container-${currentContainer + 1}`);
    console.log(`grid-container-${currentContainer + 1}`);
    div.classList.add("grid-ele");
    if(newPrice >= oldPrice){
        div.classList.add("green-bar");
    }
    else{
        div.classList.add("red-bar");
    }
    div.style.height = `${newPrice}px`;
    totalBars++;
    gridContainer.appendChild(div);
    if(totalBars % 5 == 0){
        currentContainer++;
        totalBars = 0;
    }
}

buyBtn.addEventListener("click", () => {
    btnHandler("buy");
})

sellBtn.addEventListener("click", () => {
    btnHandler("sell");
})

socket.on("load data", (data) => {
    data.forEach(element => {
        const div = document.createElement('div');
        div.classList.add("history-div");    
        div.innerHTML = element;
        historyDiv.appendChild(div);
    });
})

socket.on("new price", (newPrice) => {
    newPrice = parseFloat(newPrice).toFixed(2);
    let priceContainer = document.getElementById("price");
    let UpArrow = document.getElementById("up");
    let downArrow = document.getElementById("down");
    let percent = document.getElementById("percentChange");
    let oldPrice = parseFloat(document.getElementById("price").textContent);

    let change = (newPrice - oldPrice)/(oldPrice);
    change = (change * 100).toFixed(2);

    if(newPrice >= oldPrice){
        UpArrow.style.display = "inline";
        downArrow.style.display = "none";
    }
    else{
        UpArrow.style.display = "none";
        downArrow.style.display = "inline";
    }
    percent.textContent = `${change} %`;
    priceContainer.textContent = newPrice;

    handleChange(newPrice,oldPrice);
})