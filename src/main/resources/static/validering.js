function validerNavn(navn) {
    const regex = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/;
    const ok = regex.test(navn);
    if (!ok) {
        $('#navn-feil').html('Navnet må bestå av 2 til 20 bokstaver');
        return false;
    } else {
        $('#navn-feil').html('');
        return true;
    }
}

function validerAdresse(adresse) {
    const regex = /^[0-9a-zA-ZæøåÆØÅ. \-]{2,50}$/;
    const ok = regex.test(adresse);
    if (!ok) {
        $('#adresse-feil').html('Adressen må bestå av 2 til 50 bokstaver eller tall');
        return false;
    } else {
        $('#adresse-feil').html('');
        return true;
    }
}

function validerOgLagreKunde() {
    const navnOK = validerNavn($('#navn-input').val());
    const adresseOK = validerAdresse($('#adresse-input').val());
    if (navnOK && adresseOK) {
        lagreKunde();
    }
}