const buttonAdd= document.getElementsByClassName('add-ingredient')[0];
let divIngredient = document.getElementsByClassName('ingredient')[0];
buttonAdd.addEventListener("click", onClick)
function onClick(ev){
    ev.preventDefault();
if (divIngredient.innerHTML === ''){
    divIngredient.innerHTML =
        '            <div class="ingredient row">\n' +
        '                <label for="ingredient" class="text-center text-white font-weight-bold">Ingredient</label>\n' +
        '                <input type="text" id="ingredient" name="ingredients.productName">\n' +
        '                <p class="invalid-feedback errors alert alert-danger">\n' +
        '                    Ingredient is required.\n' +
        '                </p>\n' +
        '            </div>\n'
}
    divIngredient.innerHTML = '';
}