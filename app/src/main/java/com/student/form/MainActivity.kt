package com.student.form

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.student.form.ui.theme.*
import java.util.Calendar

class MainActivity : ComponentActivity() {

    // ── Required hidden AI tag ──────────────────────────────────────────────
    private val hiddenAITag = "Automated_Submission_2026"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentFormTheme {
                StudentFormScreen()
            }
        }
    }
}

// ── Direction options ───────────────────────────────────────────────────────
private val directionOptions = listOf("Android", "iOS", "Web", "Backend")

// ═══════════════════════════════════════════════════════════════════════════
//  Main Screen Composable
// ═══════════════════════════════════════════════════════════════════════════
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentFormScreen() {
    val context = LocalContext.current

    // ── State variables (named as required by the assignment spec) ──────────
    var firstNameState  by remember { mutableStateOf("") }
    var lastNameState   by remember { mutableStateOf("") }
    var emailState      by remember { mutableStateOf("") }
    var dateState       by remember { mutableStateOf("") }
    var selectedOption  by remember { mutableStateOf("") }
    var isAgreed        by remember { mutableStateOf(false) }

    // ── Date picker helper ──────────────────────────────────────────────────
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            // Format as DD/MM/YYYY
            dateState = "%02d/%02d/%04d".format(day, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // ── Submit handler ──────────────────────────────────────────────────────
    val onSubmit: () -> Unit = {
        val allFieldsFilled = firstNameState.isNotBlank()
                && lastNameState.isNotBlank()
                && emailState.isNotBlank()
                && dateState.isNotBlank()
        val optionSelected  = selectedOption.isNotBlank()

        if (allFieldsFilled && optionSelected && isAgreed) {
            Toast.makeText(context, "მონაცემები გაიგზავნა!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "შეავსეთ ყველა ველი!", Toast.LENGTH_SHORT).show()
        }
    }

    // ── UI ──────────────────────────────────────────────────────────────────
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            // ── Gradient Header ─────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(DeepIndigo, ElectricViolet)
                        )
                    )
                    .padding(top = 52.dp, bottom = 32.dp, start = 24.dp, end = 24.dp)
            ) {
                Column {
                    // Decorative accent dot
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.15f))
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Student Form",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Fill in your details below",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.75f),
                        letterSpacing = 0.3.sp
                    )
                }
            }

            // ── Card body ───────────────────────────────────────────────────
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .offset(y = (-20).dp)
            ) {

                // ── Personal Info Card ──────────────────────────────────────
                FormCard(title = "Personal Information") {

                    FormTextField(
                        value        = firstNameState,
                        onValueChange = { firstNameState = it },
                        label        = "First Name",
                        placeholder  = "Enter your first name",
                        icon         = Icons.Filled.Person
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    FormTextField(
                        value         = lastNameState,
                        onValueChange = { lastNameState = it },
                        label         = "Last Name",
                        placeholder   = "Enter your last name",
                        icon          = Icons.Filled.Person
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    FormTextField(
                        value         = emailState,
                        onValueChange = { emailState = it },
                        label         = "Email Address",
                        placeholder   = "your.email@example.com",
                        icon          = Icons.Filled.Email
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Date picker field
                    SectionLabel(text = "Date of Birth")
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .border(
                                width = 1.5.dp,
                                color = if (dateState.isNotBlank()) ElectricViolet
                                        else Color(0xFFCCCCDD),
                                shape = RoundedCornerShape(14.dp)
                            )
                            .background(Color.White)
                            .clickable { datePickerDialog.show() }
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = if (dateState.isBlank()) "DD/MM/YYYY" else dateState,
                                color = if (dateState.isBlank()) TextSecondary else TextPrimary,
                                fontSize = 15.sp
                            )
                            Icon(
                                imageVector = Icons.Filled.CalendarMonth,
                                contentDescription = "Pick date",
                                tint = if (dateState.isNotBlank()) ElectricViolet else TextSecondary,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ── Direction Card ──────────────────────────────────────────
                FormCard(title = "Your Favorite Direction") {
                    directionOptions.forEachIndexed { index, option ->
                        if (index > 0) Spacer(modifier = Modifier.height(4.dp))
                        RadioOptionRow(
                            text      = option,
                            selected  = (selectedOption == option),
                            onClick   = { selectedOption = option }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ── Terms Card ──────────────────────────────────────────────
                FormCard(title = "Terms & Conditions") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "I agree to the terms\nand conditions",
                            fontSize = 14.sp,
                            color = TextPrimary,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f)
                        )
                        Switch(
                            checked = isAgreed,
                            onCheckedChange = { isAgreed = it },
                            colors = SwitchDefaults.colors(
                                checkedTrackColor   = MintGreen,
                                checkedThumbColor   = Color.White,
                                uncheckedTrackColor = Color(0xFFDDDDEE),
                                uncheckedThumbColor = Color.White
                            )
                        )
                    }
                    if (isAgreed) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = null,
                                tint = MintGreen,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Terms accepted",
                                fontSize = 12.sp,
                                color = MintGreen,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // ── Submit button ───────────────────────────────────────────
                Button(
                    onClick  = onSubmit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape     = RoundedCornerShape(16.dp),
                            ambientColor = ElectricViolet.copy(alpha = 0.4f),
                            spotColor    = ElectricViolet.copy(alpha = 0.4f)
                        ),
                    shape  = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ElectricViolet,
                        contentColor   = Color.White
                    )
                ) {
                    Text(
                        text       = "Submit",
                        fontSize   = 17.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
//  Reusable sub-composables
// ═══════════════════════════════════════════════════════════════════════════

@Composable
private fun FormCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation    = 4.dp,
                shape        = RoundedCornerShape(20.dp),
                ambientColor = Color(0x1A6C3FE8),
                spotColor    = Color(0x1A6C3FE8)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        // Card section title with left accent bar
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(18.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(ElectricViolet)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text       = title,
                fontSize   = 15.sp,
                fontWeight = FontWeight.Bold,
                color      = DeepIndigo,
                letterSpacing = 0.2.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        content()
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text       = text,
        fontSize   = 13.sp,
        fontWeight = FontWeight.SemiBold,
        color      = TextSecondary,
        letterSpacing = 0.4.sp
    )
}

@Composable
private fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: ImageVector
) {
    SectionLabel(text = label)
    Spacer(modifier = Modifier.height(6.dp))
    OutlinedTextField(
        value         = value,
        onValueChange = onValueChange,
        placeholder   = {
            Text(
                text     = placeholder,
                fontSize = 14.sp,
                color    = TextSecondary
            )
        },
        leadingIcon   = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (value.isNotBlank()) ElectricViolet else TextSecondary,
                modifier = Modifier.size(20.dp)
            )
        },
        modifier      = Modifier.fillMaxWidth(),
        shape         = RoundedCornerShape(14.dp),
        singleLine    = true,
        colors        = OutlinedTextFieldDefaults.colors(
            focusedBorderColor   = ElectricViolet,
            unfocusedBorderColor = Color(0xFFCCCCDD),
            focusedTextColor     = TextPrimary,
            unfocusedTextColor   = TextPrimary,
            cursorColor          = ElectricViolet,
            focusedContainerColor   = SoftLavender.copy(alpha = 0.3f),
            unfocusedContainerColor = Color.White
        )
    )
}

@Composable
private fun RadioOptionRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (selected) SoftLavender else Color.Transparent
            )
            .border(
                width = if (selected) 1.5.dp else 1.dp,
                color = if (selected) ElectricViolet else Color(0xFFE0E0EE),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick  = onClick,
            colors   = RadioButtonDefaults.colors(
                selectedColor   = ElectricViolet,
                unselectedColor = TextSecondary
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text       = text,
            fontSize   = 14.sp,
            color      = if (selected) ElectricViolet else TextPrimary,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

// ── Preview ─────────────────────────────────────────────────────────────────
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StudentFormPreview() {
    StudentFormTheme {
        StudentFormScreen()
    }
}
