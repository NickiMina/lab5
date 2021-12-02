# lab5
Aplikacja, która wyświetla wyszukiwarkę, a następnie po wpisaniu danej frazy, wyszukuje odpowiedniego pracownika.
Rekordy pracowników wyświetlane są ze strony https://panoramafirm.pl/

Sposob dzialania:
Nalezy wpisac w okno przegladarki url: http://localhost:8080/page i wyswietli się wyszukiwarka.
Następnie w okno nalezy wpisac pożądaną frazę np: mechanik.
Zostaniemy przeniesieni na http://localhost:8080/page/users
Strona wyswietli kilka wynikow, ze strony panoramafirm, dotyczacych frazy "mechanik" w postaci:
NAZWA
LINK DO PRACOWNIKA
PRZYCISK>>WYGENERUJ WIZYTOWKE<<
Po kliknieciu w przycisk zostanie wygenerowany plik vcf. 
Adres do wygenerowania wizytowki: http://localhost:8080/card/{user}

