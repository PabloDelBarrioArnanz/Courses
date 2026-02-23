import { useEffect, useState } from "react";
import { ActivityIndicator, FlatList } from "react-native";
import { getLatestGames } from "../lib/metacritic";
import { GameCard } from "./GameCard";
import { Screen } from "./Screen";

export function Main() {
  const [games, setGames] = useState([]);

  useEffect(() => {
    getLatestGames().then((data) => {
      setGames(data);
    });
  }, []);

  return (
    <Screen>
      {games.length === 0 ? (
        <ActivityIndicator color={"#fff"} size={"large"} />
      ) : (
        <FlatList
          data={games}
          keyExtractor={(game) => game.slug}
          renderItem={({ item, index }) => (
            <GameCard game={item} index={index} />
          )}
        />
      )}
    </Screen>
  );
}
