# cashpoint

WYMAGANIA FUNKCJONALNE
Program korzysta z dwóch plików csv (w załączniku):
users.csv - przechowuje login, hasło i numer konta (w formacie IBAN)
accounts.csv - przechowuje numer konta i ilość pieniędzy na koncie
1. Podczas uruchomienia program powinien wczytać te pliki.
2. Zanim będzie można wykonać operację, należy się zalogować podając odpowiedni login i hasło. Powinno być możliwe wylogowanie i zalogowanie na inne konto.
3. Po zalogowaniu, do momentu wylogowania, powinna być dostępna historia wykonanych w danej sesji działań. Powinna być możliwość cofnięcia wykonanej akcji.
4. Dla zalogowanego użytkownika dostępne powinny być operacje:
help
withdraw
deposit
exit
transfer
undo
balance
5. Program powinien być odporny na błędy i wyłudzenia:
wypłata/transfer zbyt dużej ilości środków
transfer na nieistniejące konto