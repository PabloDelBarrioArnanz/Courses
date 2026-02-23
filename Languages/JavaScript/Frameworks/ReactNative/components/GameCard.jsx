import PropTypes from "prop-types";
import { useEffect, useRef } from "react";
import { Animated, Image, StyleSheet, Text, View, Pressable } from "react-native";
import { Score } from "./Score";

import { Link } from "expo-router";

export function GameCard({ game }) {
  return (
    <Link asChild href={`/${game.slug}`}>
      <Pressable className="active:opacity-70 border border-black active:border-white/50 mb-2 bg-slate-500/10 rounded-xl p-4">
        <View
          key={game.slug}
          className="flex-row gap-4"
        >
          <Image source={{ uri: game.image }} style={styles.image} />
          <View className="flex-shrink">
            <Text className="mb-1" style={styles.title}>
              {game.title}
            </Text>
            <Score score={game.score} maxScore={100} />
            <Text style={styles.description} className="mt-2 flex-shrink-0">
              {game.description} - {game.releaseDate}
            </Text>
          </View>
        </View>
      </Pressable>
    </Link>
  );
}

export function AnimatedGameCard({ game, index }) {
  const opacity = useRef(new Animated.Value(0)).current;

  useEffect(() => {
    Animated.timing(opacity, {
      toValue: 1,
      duration: 500,
      delay: index * 150,
      useNativeDriver: true,
    }).start();
  }, [index, opacity]);

  return (
    <Animated.View style={[styles.card, { opacity }]}>
      <Image source={{ uri: game.image }} style={styles.image} />
      <Text style={styles.title}>{game.title}</Text>
      <Text style={styles.score}>{game.score}</Text>
      <Text style={styles.description}>
        {game.description} - {game.releaseDate}
      </Text>
    </Animated.View>
  );
}

const styles = StyleSheet.create({
  card: {
    marginBottom: 42,
  },
  image: {
    width: 107,
    height: 147,
    borderRadius: 8,
  },
  title: {
    fontSize: 20,
    fontWeight: "bold",
    color: "#fff",
    marginTop: 10,
  },
  description: {
    fontSize: 16,
    color: "#eee",
  },
  score: {
    fontSize: 20,
    fontWeight: "bold",
    color: "green",
    marginBottom: 10,
  },
});

GameCard.propTypes = {
  game: PropTypes.shape({
    slug: PropTypes.string.isRequired,
    image: PropTypes.string.isRequired,
    title: PropTypes.string.isRequired,
    score: PropTypes.number.isRequired,
    description: PropTypes.string.isRequired,
    releaseDate: PropTypes.string.isRequired,
  }).isRequired,
};

AnimatedGameCard.propTypes = {
  game: PropTypes.shape({
    image: PropTypes.string.isRequired,
    title: PropTypes.string.isRequired,
    score: PropTypes.number.isRequired,
    description: PropTypes.string.isRequired,
    releaseDate: PropTypes.string.isRequired,
  }).isRequired,
  index: PropTypes.number.isRequired,
};
