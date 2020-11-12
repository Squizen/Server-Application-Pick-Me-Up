-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 18 Paź 2020, 17:34
-- Wersja serwera: 10.4.11-MariaDB
-- Wersja PHP: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `pickmeup_database`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `activation_code`
--

CREATE TABLE `activation_code` (
  `id_activation_code` int(11) NOT NULL,
  `serial_number` varchar(15) COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `offered_ride`
--

CREATE TABLE `offered_ride` (
  `id_offered_ride` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date_of_ride` date NOT NULL,
  `time_of_ride` time NOT NULL,
  `number_of_free_seats` int(2) NOT NULL,
  `ride_category` int(1) NOT NULL,
  `from_where` varchar(100) COLLATE utf8mb4_polish_ci NOT NULL,
  `to_where` varchar(100) COLLATE utf8mb4_polish_ci NOT NULL,
  `user_comment` varchar(250) COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `seat`
--

CREATE TABLE `seat` (
  `id_seat` int(11) NOT NULL,
  `id_offered_ride` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `id_activation_code` int(11) NOT NULL,
  `user_name` varchar(20) COLLATE utf8mb4_polish_ci NOT NULL,
  `user_surname` varchar(30) COLLATE utf8mb4_polish_ci NOT NULL,
  `user_email` varchar(30) COLLATE utf8mb4_polish_ci NOT NULL,
  `user_password` varchar(30) COLLATE utf8mb4_polish_ci NOT NULL,
  `user_phone_number` varchar(15) COLLATE utf8mb4_polish_ci NOT NULL,
  `user_car` varchar(100) COLLATE utf8mb4_polish_ci NOT NULL,
  `user_description` varchar(250) COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wanted_ride`
--

CREATE TABLE `wanted_ride` (
  `id_wanted_ride` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date_of_ride` date NOT NULL,
  `time_of_ride` time NOT NULL,
  `ride_category` int(2) NOT NULL,
  `from_where` varchar(100) COLLATE utf8mb4_polish_ci NOT NULL,
  `to_where` varchar(100) COLLATE utf8mb4_polish_ci NOT NULL,
  `user_comment` varchar(250) COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `activation_code`
--
ALTER TABLE `activation_code`
  ADD PRIMARY KEY (`id_activation_code`);

--
-- Indeksy dla tabeli `offered_ride`
--
ALTER TABLE `offered_ride`
  ADD PRIMARY KEY (`id_offered_ride`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeksy dla tabeli `seat`
--
ALTER TABLE `seat`
  ADD PRIMARY KEY (`id_seat`),
  ADD KEY `id_wanted_ride` (`id_offered_ride`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `id_activation_code` (`id_activation_code`);

--
-- Indeksy dla tabeli `wanted_ride`
--
ALTER TABLE `wanted_ride`
  ADD PRIMARY KEY (`id_wanted_ride`),
  ADD KEY `id_user` (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `activation_code`
--
ALTER TABLE `activation_code`
  MODIFY `id_activation_code` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `offered_ride`
--
ALTER TABLE `offered_ride`
  MODIFY `id_offered_ride` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `seat`
--
ALTER TABLE `seat`
  MODIFY `id_seat` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `wanted_ride`
--
ALTER TABLE `wanted_ride`
  MODIFY `id_wanted_ride` int(11) NOT NULL AUTO_INCREMENT;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `offered_ride`
--
ALTER TABLE `offered_ride`
  ADD CONSTRAINT `offered_ride_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Ograniczenia dla tabeli `seat`
--
ALTER TABLE `seat`
  ADD CONSTRAINT `seat_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `seat_ibfk_2` FOREIGN KEY (`id_offered_ride`) REFERENCES `offered_ride` (`id_offered_ride`);

--
-- Ograniczenia dla tabeli `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`id_activation_code`) REFERENCES `activation_code` (`id_activation_code`);

--
-- Ograniczenia dla tabeli `wanted_ride`
--
ALTER TABLE `wanted_ride`
  ADD CONSTRAINT `wanted_ride_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
