import PropTypes from "prop-types";
import { Text, View } from "react-native";

export function Score({ score, maxScore = 100 }) {
  const getColors = () => {
    const percentage = (score / maxScore) * 100;
    if (percentage < 40) return "bg-red-500 text-white";
    else if (percentage < 65) return "bg-yellow-500 text-white";
    return "bg-green-500/80";
  };

  return (
    <View
      className={`${getColors()} w-8 h-8 rounded-full justify-center items-center`}
    >
      <Text className="text-lg font-bold">{score}</Text>
    </View>
  );
}

Score.propTypes = {
  score: PropTypes.number.isRequired,
  maxScore: PropTypes.number,
};
