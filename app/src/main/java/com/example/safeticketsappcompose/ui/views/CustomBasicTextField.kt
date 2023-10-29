import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomStyledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        decorationBox = { innerTextField ->
            Box(
                Modifier
                    .background(Color.LightGray, RoundedCornerShape(percent = 30))
                    .padding(16.dp)
            ) {

                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = LocalTextStyle.current.copy(
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )
                    )
                }
                innerTextField()
            }
        },
        modifier = modifier
    )
}
