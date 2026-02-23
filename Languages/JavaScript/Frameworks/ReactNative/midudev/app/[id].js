import { Link, useLocalSearchParams, Stack } from "expo-router";
import { Text, View } from "react-native";
import { Screen } from "../components/Screen";

export default function Detail() {
  const { id } = useLocalSearchParams();

  return (
    <Screen>
      <Stack.Screen
        options={{
          headerStyle: { backgroundColor: "#ffee00" },
          headerTint: "black",
          headerLeft: () => {},
          headerTitle: "Game detail",
          headerRight: () => {},
        }}
      />
      <View>
        <Text className="text-white font-bold mb-8 text-2xl">
          Detalle del juego con id {id}
        </Text>
        <Link href="/" className="text-blue-500">
          Volver atrás
        </Link>
      </View>
    </Screen>
  );
}
