$(document).ready(function () {

    $('.add-ingredient').click(function () {

    let newRow = jQuery('<div class="ingredient row" >\n' +
        '                <div class="col-lg-6 mb-3">\n' +
        '                    Name\n' +
        '                    <input type="text" name="ingredients.productName"\n' +

        '                           class="form-control form-control-lg" >\n' +
        '                    <p class="invalid-feedback errors alert alert-danger">\n' +
        '                        Ingredient is required.\n' +
        '                    </p>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>');

    $('.ingredient').append(newRow);


    });
});