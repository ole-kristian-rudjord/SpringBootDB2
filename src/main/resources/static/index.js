$(function () {
    hentKunder();
});

function lagreKunde() {
    let kunde = {
        navn: $('#navn-input').val(),
        adresse: $('#adresse-input').val()
    }

    $('#navn-input').val('');
    $('#adresse-input').val('');

    $.post("/lagre", kunde, function () {
        hentKunder();
    })
    .fail(function (status) {
        if (status.status == 422) {
            $('#feil-melding').html('Feil i database, prøv igjen senere');
        }
        else {
            $('#feil-melding').html('Valideringsfeil, prøv igjen senere');
        }
    });
}

function hentKunder() {
    $.get("/hentAlle", function (kunder) {
        formaterKunder(kunder);
    })
    .fail(function () { // gjør om .fail innhold til function
        $('#feil-melding').html('Feil i database, prøv igjen senere');
    });
}

function formaterKunder(kunder) {
    let ut = '<table class="table table-striped">' +
        '<tr>' +
            '<th>Navn</th>' +
            '<th>Adresse</th>' +
            '<th>Passord</th>' +
            '<th></th>' +
            '<th></th>' +
        '</tr>';
    for (let kunde of kunder) {
        ut += '<tr>' +
                `<td>${kunde.navn}</td>` +
                `<td>${kunde.adresse}</td>` +
                `<td>Passord</td>`
                `<td><a class="btn btn-primary" href="endreKunde.html?id=${kunde.id}">Endre</a></td>` +
                `<td><button class="btn btn-danger" onclick="slettEnKunde(${kunde.id})">Slett</button></td>` +
            '</tr>';
    }
    ut += '</table>';
    $('#kunder-div').html(ut);
}

function slettKunder() {
    $.get("/slettAlle", function (data) { // side 23, hvorfor skal det være function (data) når data ikke brukes. Og hvorfor er det ikke post ettersom man ikke henter noe fra databasen, men fjerner noe. Er post bare for å legge til?
        hentKunder();
    })
    .fail(function () {
        $('#feil-melding').html('Feil i database, prøv igjen senere');
    });
}

function slettEnKunde(id) {
    const url = "/slettEnKunde?id="+id;
    $.get(url, function() {
        window.location.href = "/";
    })
    .fail(function () {
        $('#feil-melding').html('Feil i database, prøv igjen senere');
    });
}

