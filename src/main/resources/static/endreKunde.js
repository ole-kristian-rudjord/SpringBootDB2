$(function () {
    const id = window.location.search.substring(1); // trenger bedre forklaring
    const url = "/hentEnKunde?"+id;
    $.get(url, function(kunde) {
        $('#endre-id-input').val(kunde.id);
        $('#endre-navn-input').val(kunde.navn);
        $('#endre-adresse-input').val(kunde.adresse);
    });
});

function endreKunden() {
    const Kunde = {
        id: $('#endre-id-input').val(),
        navn: $('#endre-navn-input').val(),
        adresse: $('#endre-adresse-input').val()
    }

    $.post("/endreEnKunde", Kunde, function () {
        window.location.href = "/";
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