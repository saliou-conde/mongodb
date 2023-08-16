# Interview
**Ausgangssituation I** 

Die Stadtwerke kaufen Strommengen über Termingeschäfte im Voraus, um den erwarteten Gesamtverbrauch ihrer Endverbraucher zu decken. Die Verbrauchsprognose wird regelmäßig angepasst. Wenn am Tag vor der Lieferung für einzelne Viertelstunden eine Über- oder Unterdeckung vorliegt, können die Differenzmengen über Spot-Geschäfte auf der Strombörse beschafft oder veräußert werden.
Da die Stadtwerke keinen direkten Zugang zur Strombörse haben, bietet Trianel als Dienstleister den Zugang zur Börse an. Trianel bündelt die Spot-Mengen der Kunden und beschafft oder veräußert den Saldo im eigenen Namen an der Strombörse. Die Stadtwerke erhalten dann ihre Spot-Menge von Trianel zu denselben Konditionen wie das Börsengeschäft, zuzüglich einer Servicegebühr.
Trianel bietet auch die Dienstleistung an, die Verbrauchsprognose für die Kunden zu erstellen und auf dieser Basis die notwendige Spotmenge zu liefern. Einige, aber nicht alle Kunden nutzen diese Dienstleistung.

**Ausgangssituation II**

Trianel erfasst alle Geschäfte, an denen sie als Käufer oder Verkäufer beteiligt ist, in ihrem Handelssystem A, unabhängig davon, ob der Geschäftspartner die Strombörse oder ein Stadtwerk ist. Die Geschäfte und Verbräuche des Kunden werden in einem anderen Handelssystem B aus Sicht des Kunden erfasst. Jeder Kunde ist als eigener Mandant in Handelssystem B angelegt.
Am Tag vor der Lieferung müssen die Stadtwerke den Stromnetzbetreibern mitteilen, welche Strommenge sie mit welchem Geschäftspartner austauschen wollen (Fahrplananmeldung). Trianel muss ebenfalls ihre beabsichtigten Liefer- und Beschaffungsmengen bei den Netzbetreibern ankündigen. Für Geschäfte zwischen Trianel und dem Kunden müssen daher zwei identische Fahrplananmeldungen bei den Netzbetreibern eingehen.
Trianel bietet den Stadtwerken die Durchführung der Fahrplananmeldungen in ihrem Namen an. Die Daten für die Fahrplananmeldungen der Kunden stammen aus Handelssystem B, in dem alle Geschäfte aus Sicht der Kunden erfasst sind. Die Daten für die Fahrplananmeldungen von Trianel stammen dagegen aus Handelssystem A.


**Konkrete Anforderung der Fachabteilung:**

Basierend auf den Anforderungen des Händlers und des aktuellen Prozesses empfehle ich eine Softwarelösung, die folgende Funktionen bietet:
1.	Bestellungsverwaltung: Die Software sollte in der Lage sein, Bestellungen von Kunden automatisch zu erfassen, zu aggregieren und anhand von vordefinierten Regeln und Deadline zu priorisieren. Es sollte möglich sein, Bestellungen manuell hinzuzufügen, zu bearbeiten oder zu löschen. Die Kunden sollten in der Software als separate Mandanten angelegt werden, um ihre Daten getrennt zu halten.
2.	Plausibilitätsprüfung: Vor der Platzierung des Gebots an der Strombörse sollte die Software eine automatische Plausibilitätsprüfung durchführen, die die Konsistenz der Bestellungen sicherstellt. Hierbei sollten auch die Mengen je Kunde und das Gesamtergebnis auf Plausibilität geprüft werden.
3.	Automatisierte Berechnungen: Die Software sollte die Möglichkeit bieten, Spotmenge und Fahrplananmeldung automatisch zu berechnen und durchzuführen. Hierbei sollten auch kurzfristige Änderungen der Verbrauchsprognose berücksichtigt werden können.
4.	Deadline-Überwachung: Die Software sollte die Einhaltung der Deadlines für Bestellungen und Fahrplananmeldungen überwachen und automatisch Erinnerungen senden, wenn eine Deadline bevorsteht oder überschritten wird.
5.	Transparenz: Die Software sollte dem Händler jederzeit einen Überblick darüber geben, von welchen Kunden bereits eine Spot-Bestellung vorliegt und von welchen nicht.
6.	Integration: Die Software sollte in der Lage sein, nahtlos mit den Handelssystemen A und B sowie dem Fahrplananmeldungstool zu integrieren, um alle Geschäfte automatisch zu erfassen und rechtzeitig zu buchen.
7.	Reporting: Die Software sollte eine umfassende Berichtsfunktion bieten, um dem Händler einen Überblick über alle durchgeführten Geschäfte und Transaktionen zu geben.
      Zusammenfassend sollte die Software eine zentrale Plattform für die Bestellungsverwaltung, Plausibilitätsprüfung, automatisierte Berechnungen, Deadline-Überwachung, Transparenz, Integration und Reporting bieten, um den Prozess der Bestellverwaltung und -platzierung zu vereinfachen und zu optimieren.



**Lösungsvorschlag**

**Datenflüsse und Zeitpunkte:**

•	Die Kunden senden bis spätestens 13:00 Uhr täglich ihre Spot-Bestellungen per E-Mail an den Händler.

•	Bis spätestens 13:10 Uhr können einige Kunden ihre Bestellungen an den Händler schicken.

•	Die Bestellungen werden in Handelssystem B erfasst.

•	Der Händler kann die Bestellungen und das Gesamtergebnis bis spätestens 13:30 Uhr plausibilisieren.

•	Bis spätestens 14:00 Uhr muss die Fahrplananmeldung für den Folgetag bei den Netzbetreibern erfolgt sein.

•	Falls der Händler die Verbrauchsprognose für den Kunden erstellt, muss er die Spotmenge auf Basis der Daten aus Handelssystem B (neu) bis ca. 12:50 Uhr berechnen.

•	Alle Geschäfte, die von Trianel als Käufer oder Verkäufer getätigt wurden, werden in Handelssystem A erfasst.

•	Alle Geschäfte, an denen das Stadtwerk beteiligt ist, werden in einem anderen Handelssystem B erfasst.

•	Trianel bietet den Stadtwerken die Durchführung der Fahrplananmeldungen in ihrem Namen an, wobei die Daten für die Fahrplananmeldungen der Kunden aus Handelssystem B und die Daten für die Fahrplananmeldungen von Trianel aus Handelssystem A stammen.

**Weitere Informationen, die im Rahmen der Analyse und Anforderungsklärung erhoben werden sollten, sind:**

•	Wie viele Mitarbeiter benötigen Zugriff auf die Softwarelösung und welche Rollen haben sie?

•	Welche Sicherheitsanforderungen müssen erfüllt werden?

•	Wie sollen Fehlermeldungen und Ausnahmefälle behandelt werden?

•	Wie werden Änderungen an den Datenmodellen gehandhabt?

•	Wie soll die Integration mit anderen Systemen erfolgen?

•	Welche Performance-Anforderungen gibt es?

•	Genauere Details zur Anforderung, welche Prozessschritte automatisiert werden sollen

•	Anzahl der Kunden und voraussichtliche Anzahl der Bestellungen pro Tag

•	Details zur Komplexität der Fahrplananmeldungen bei den Netzbetreibern

•	Die Art und Qualität der Prognosemodelle, die zur Berechnung der Spotmengen verwendet werden

•	Details zur Größe und Komplexität der Datenmengen, die in den verschiedenen Systemen verarbeitet werden

•	Informationen über die IT-Infrastruktur, auf der die Softwarelösung aufgebaut wird

**Die wesentlichen Einflussfaktoren auf die Komplexität der Lösung sind:**

•	Der Anzahl der Kunden und Bestellungen pro Tag

•	Der Komplexität der Fahrplananmeldungen bei den Netzbetreibern

•	Der Genauigkeit und Qualität der Prognosemodelle zur Berechnung der Spotmengen

•	Der Notwendigkeit, mehrere Systeme zu integrieren und synchronisieren

•	Die Vielzahl der Kunden und Mandanten im Handelssystem B

•	Die unterschiedlichen Deadline-Vereinbarungen mit den Kunden

•	Die Notwendigkeit, alle Geschäfte sowohl in Handelssystem A als auch in Handelssystem B zu erfassen und zu verwalten

•	Die Notwendigkeit, die Bestellungen der Kunden bis spätestens 13:30 Uhr zu plausibilisieren und bis spätestens 14:00 Uhr die Fahrplananmeldungen durchzuführen

•	Die Notwendigkeit, den Prozess weitgehend zu automatisieren

**Die Schritte zur Entwicklung der individuellen Softwarelösung könnten wie folgt aussehen:**

1.	Anforderungsanalyse und -definition: Erfassung aller Anforderungen an das System und Definition der Funktionalitäten und Prozesse, die implementiert werden sollen
2.	Design: Erstellung eines Systemdesigns, das alle Anforderungen berücksichtigt und eine robuste und skalierbare Architektur bietet
3.	Entwicklung: Implementierung der Software unter Verwendung moderner Entwicklungsmethoden und -tools
4.	Testen: Umfassendes Testen der Software, um sicherzustellen, dass sie korrekt funktioniert und allen Anforderungen entspricht
5.	Bereitstellung: Bereitstellung der Software in einer Produktionsumgebung, die sicher und stabil ist und den Betrieb rund um die Uhr unterstützen kann
6.	Wartung und Support: Laufende Wartung und Unterstützung des Systems, einschließlich Fehlerbehebung und kontinuierlicher Verbesserung

Die größten Herausforderungen bei der Entwicklung und dem Betrieb der Softwarelösung könnten in der Integration und Synchronisierung von mehreren Systemen liegen, in der Sicherstellung der Skalierbarkeit und Robustheit des Systems sowie in der Einhaltung von gesetzlichen Bestimmungen und regulatorischen Anforderungen im Energiesektor.

