-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2026 at 05:52 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `recording_studio_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `albums`
--

CREATE TABLE `albums` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `year` int(11) NOT NULL,
  `artist_id` int(11) NOT NULL,
  `is_archived` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `albums`
--

INSERT INTO `albums` (`id`, `name`, `year`, `artist_id`, `is_archived`) VALUES
(1, 'Limasawa Street', 2019, 1, 0),
(2, 'Cutterpillow', 1995, 2, 0),
(3, 'Malaya', 2018, 3, 0),
(4, 'Pagsibol', 2021, 4, 0),
(5, 'Episode', 2021, 5, 0),
(6, 'Coco', 1990, 1, 0),
(7, 'Coco', 1990, 1, 0),
(8, 'Bigotilyo', 2003, 6, 0),
(9, 'Maharot', 2006, 7, 0),
(10, 'Patutunguhan', 2023, 8, 0),
(11, 'CLAPCLAPCLAP!', 2019, 9, 0),
(12, 'Fuchsiang Pag-Ibig', 2007, 10, 0),
(13, 'Letters Never Sent', 2019, 11, 0),
(14, 'Perfectly Imperfect', 2014, 12, 0),
(15, 'Talaarawan', 2024, 13, 0),
(16, 'Langit Mong Bughaw', 2019, 14, 0),
(17, 'Capacities', 2012, 15, 0);

-- --------------------------------------------------------

--
-- Table structure for table `artists`
--

CREATE TABLE `artists` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `is_archived` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `artists`
--

INSERT INTO `artists` (`id`, `name`, `is_archived`) VALUES
(1, 'Ben&Ben', 0),
(2, 'Eraserheads', 0),
(3, 'Moira Dela Torre', 0),
(4, 'SB20', 0),
(5, 'Zack Tabudlo', 0),
(6, 'Parokya Ni Edgar', 0),
(7, 'Kamikazee', 0),
(8, 'Cup of Joe', 0),
(9, 'IV of Spades', 0),
(10, 'Silent Sanctuary', 0),
(11, 'Arthur Nery', 0),
(12, 'Sarah Geronimo', 0),
(13, 'BINI', 0),
(14, 'December Avenue', 0),
(15, 'Up Dharma Down', 0),
(16, 'Yuno', 0),
(17, 'Immanuel', 0);

-- --------------------------------------------------------

--
-- Table structure for table `playlists`
--

CREATE TABLE `playlists` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT current_timestamp(),
  `user_id` int(11) NOT NULL,
  `is_archived` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `playlists`
--

INSERT INTO `playlists` (`id`, `name`, `date_created`, `user_id`, `is_archived`) VALUES
(6, 'Senti OPM Hits', '2026-11-18 14:27:09', 2, 0),
(7, '90s Pinoy Rock Classics', '2026-11-18 14:27:09', 2, 0),
(8, 'P-Pop Power & Hype', '2026-11-18 14:27:09', 3, 0),
(9, 'Acoustic Chill Sa Tag-ulan', '2026-11-18 14:27:09', 4, 0),
(10, 'Harana Vibes', '2026-11-18 14:27:09', 5, 0),
(11, 'Inuman Sessions', '2026-11-18 14:30:01', 2, 0),
(12, 'Roadtrip OPM', '2026-11-18 14:30:01', 3, 0),
(13, 'P-Pop Dance Party', '2026-11-18 14:30:01', 4, 0),
(14, 'Late Night Hugot', '2026-11-18 14:30:01', 5, 0),
(15, 'Bandang Pinoy Classics', '2026-11-18 14:30:01', 2, 0),
(16, 'Chill Acoustic Sundays', '2026-11-18 14:30:01', 3, 0),
(17, 'Coffee Shop Vibes', '2026-11-18 14:30:01', 4, 0),
(18, 'Birit & Pop Anthems', '2026-11-18 14:30:01', 5, 0),
(19, 'Indie Favorites', '2026-11-18 14:30:01', 2, 0),
(20, 'Soundtrip Sa Biyahe', '2026-11-18 14:30:01', 3, 0),
(21, 'MyPlaylist', '2026-11-22 06:26:54', 8, 0),
(24, 'myfaveplaylist', '2026-11-23 12:06:16', 9, 0);

-- --------------------------------------------------------

--
-- Table structure for table `playlist_songs`
--

CREATE TABLE `playlist_songs` (
  `playlist_id` int(11) NOT NULL,
  `song_id` int(11) NOT NULL,
  `is_archived` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `playlist_songs`
--

INSERT INTO `playlist_songs` (`playlist_id`, `song_id`, `is_archived`) VALUES
(6, 1, 0),
(6, 5, 0),
(6, 9, 0),
(6, 11, 0),
(6, 12, 0),
(7, 3, 0),
(7, 4, 0),
(7, 13, 0),
(7, 14, 0),
(8, 7, 0),
(8, 8, 0),
(8, 18, 0),
(9, 2, 0),
(9, 10, 0),
(9, 16, 0),
(9, 19, 0),
(10, 9, 0),
(10, 15, 0),
(11, 20, 0),
(12, 17, 0),
(21, 1, 0),
(21, 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `songs`
--

CREATE TABLE `songs` (
  `id` int(11) NOT NULL,
  `title` varchar(150) NOT NULL,
  `genre` varchar(50) NOT NULL,
  `length` varchar(10) NOT NULL,
  `album_id` int(11) NOT NULL,
  `is_archived` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `songs`
--

INSERT INTO `songs` (`id`, `title`, `genre`, `length`, `album_id`, `is_archived`) VALUES
(1, 'Kathang Isip', 'Folk Pop', '5:18', 1, 0),
(2, 'Pagtingin', 'Indie Folk', '3:47', 1, 0),
(3, 'Ang Huling El Bimbo', 'Alternative Rock', '7:29', 2, 0),
(4, 'Overdrive', 'Pinoy Rock', '5:06', 2, 0),
(5, 'Tagpuan', 'Ballad', '4:15', 3, 0),
(6, 'Malaya', 'Acoustic Pop', '4:12', 3, 1),
(7, 'MAPA', 'P-Pop', '4:35', 4, 0),
(8, 'What?', 'P-Pop', '4:30', 4, 0),
(9, 'Binibini', 'Pop R&B', '3:41', 5, 0),
(10, 'Nangangamba', 'Indie Pop', '3:30', 5, 0),
(11, 'Harana', 'Acoustic Rock', '3:02', 6, 0),
(12, 'Narda', 'Pinoy Rock', '4:39', 7, 0),
(13, 'Tingin', 'Indie Pop', '3:42', 8, 0),
(14, 'Mundo', 'Alternative Rock', '5:49', 9, 1),
(15, 'Kundiman', 'Alternative Rock', '5:08', 10, 0),
(16, 'Pagsamo', 'R&B / Soul', '4:57', 11, 0),
(17, 'Kilometro', 'Pop', '3:28', 12, 0),
(18, 'Pantropiko', 'P-Pop', '3:45', 13, 0),
(19, 'Sa Ngalan Ng Pag-Ibig', 'Pop Rock', '4:40', 14, 0),
(20, 'Tadhana', 'Indie Pop', '3:42', 15, 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'USER',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_archived` tinyint(4) NOT NULL DEFAULT 0
) ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `role`, `created_at`, `is_archived`) VALUES
(1, 'admin', '$2a$14$4szH2mW6Pj66SIZ7/h.31e9elh3lhZnhO1KyHqqkfoxPIr2cU5yZO', 'admin@recordingstudio.ph', 'ADMIN', '2026-11-18 14:26:51', 0),
(2, 'user', '$2a$14$A3lIISVqdE1QP4hmTAOnre65H.3PuoRXMm.UJ9QBqqK557OXwI8hq', 'user@gmail.com', 'USER', '2026-11-18 14:26:51', 0),
(3, 'summer', 'Summer2026!', 'summer@gmail.com', 'USER', '2026-11-18 14:26:51', 0),
(4, 'winter', 'Winter2026!', 'winter@gmail.com', 'USER', '2026-11-18 14:26:51', 0),
(5, 'fall', 'Fall2026!', 'fall@gmail.com', 'USER', '2026-11-18 14:26:51', 0),
(6, 'spring', 'Spring2026!', 'spring@gmail.com', 'USER', '2026-11-18 14:26:51', 0),
(7, 'yuno', '$2a$10$4XYrdP9YlbXKm0O2kzOfw.OmgVKEpS7GYax18PleBc0Ktu1mieIku', 'yuno@gmail.com', 'USER', '2026-11-19 11:02:01', 0),
(8, 'testuser', '$2a$10$Qj.aCxnd3QXvMTUPEj1rfe2F1NqsVfZ8SosQ3e5UTGAataR80lKfa', 'testuser@gmail.co', 'USER', '2026-11-22 04:58:39', 0),
(9, 'test', '$2a$10$pmNzTD0mAeR/0SNGWhoSpeYfcL9PxsrA.GzjXcclED.jvG5nCZ4Ym', 'test@gmail.com', 'USER', '2026-11-23 10:19:29', 0),
(10, 'jareth', '$2a$10$4dfAFeewKEAZrWMIfLck.ej0eS8m4l7oOxgJh94BtzINqN4FNESFW', 'jareth@gmail.com', 'USER', '2026-11-24 04:33:36', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `albums`
--
ALTER TABLE `albums`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_albums_artist` (`artist_id`);

--
-- Indexes for table `artists`
--
ALTER TABLE `artists`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `playlists`
--
ALTER TABLE `playlists`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_playlists_user` (`user_id`);

--
-- Indexes for table `playlist_songs`
--
ALTER TABLE `playlist_songs`
  ADD PRIMARY KEY (`playlist_id`,`song_id`),
  ADD KEY `fk_playlist_songs_song` (`song_id`);

--
-- Indexes for table `songs`
--
ALTER TABLE `songs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_songs_album` (`album_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `albums`
--
ALTER TABLE `albums`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `artists`
--
ALTER TABLE `artists`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `playlists`
--
ALTER TABLE `playlists`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `songs`
--
ALTER TABLE `songs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `albums`
--
ALTER TABLE `albums`
  ADD CONSTRAINT `fk_albums_artist` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `playlists`
--
ALTER TABLE `playlists`
  ADD CONSTRAINT `fk_playlists_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `playlist_songs`
--
ALTER TABLE `playlist_songs`
  ADD CONSTRAINT `fk_playlist_songs_playlist` FOREIGN KEY (`playlist_id`) REFERENCES `playlists` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_playlist_songs_song` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `songs`
--
ALTER TABLE `songs`
  ADD CONSTRAINT `fk_songs_album` FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
